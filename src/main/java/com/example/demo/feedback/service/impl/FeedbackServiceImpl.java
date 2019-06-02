package com.example.demo.feedback.service.impl;

import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.util.StringTools;
import com.example.demo.feedback.dto.request.FeedbackInfo;
import com.example.demo.feedback.dto.request.FeedbackWhere;
import com.example.demo.feedback.repository.FeedbackReplyRepository;
import com.example.demo.feedback.repository.FeedbackRepository;
import com.example.demo.feedback.repository.entity.FeedbackEntity;
import com.example.demo.feedback.repository.entity.FeedbackReplyEntity;
import com.example.demo.feedback.service.FeedbackService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackRepository feedbackRepository;
    @Resource
    private FeedbackReplyRepository feedbackReplyRepository;
    @Resource
    private EntityManager entityManager;

    @Override
    public void uploadFeedback(FeedbackInfo feedbackInfo, int userId) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();
        BeanUtils.copyProperties(feedbackInfo, feedbackEntity);
        feedbackEntity.setUserId(userId);
        feedbackEntity.setMobile(StringTools.getMobileStr(userId));
        feedbackEntity.setUserName("xiaoming" + userId);
        feedbackEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        feedbackEntity.setIgnoreCount(0);
        feedbackEntity.setReplyCount(0);
        feedbackRepository.save(feedbackEntity);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void saveFeedbackInfo(int replyType, int id, String context) {
        FeedbackReplyEntity feedbackReply = new FeedbackReplyEntity();
        if(replyType == 1){
            feedbackReply.setFeedbackId(id);
            feedbackReply.setReplyContext(context);
            feedbackReply.setReplyType(1);
            Query query = entityManager.createNativeQuery("update feedback set reply_count = reply_count + 1 where id = ?");
            query.setParameter(1,id);
            int successCount = query.executeUpdate();
            entityManager.flush();
            System.err.println("成功更新"+successCount+"条数据");
        }else if(replyType == 2){
            feedbackReply.setFeedbackId(id);
            feedbackReply.setReplyType(2);
            Query query = entityManager.createNativeQuery("update feedback set ignore_count = ignore_count + 1 where id = ?");
            query.setParameter(1,id);
            int successCount = query.executeUpdate();
            entityManager.flush();
            System.err.println("成功更新"+successCount+"条数据");
        }else{
            throw new BusinessException("没有此类型的回复id");
        }
        if(feedbackReply.getFeedbackId() != null && feedbackReply.getReplyType() != null){
            feedbackReplyRepository.saveAndFlush(feedbackReply);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean[] canFeedback(int id) {
        List<FeedbackReplyEntity> listReply = feedbackReplyRepository.findByFeedbackIdAndReplyType(id, 1);
        List<FeedbackReplyEntity> listIgnore = feedbackReplyRepository.findByFeedbackIdAndReplyType(id, 2);
        boolean boolIgnore = true;
        boolean boolReply = true;
        if(listIgnore.size() > 0 || listReply.size() > 0){
            boolIgnore = false;
        }
        if(listReply.size() > 5){
            boolReply = false;
        }
        return new boolean[]{boolReply,boolIgnore};
    }

    @Transactional(readOnly = true)
    @Override
    public Object[] getFeedbackDetail(FeedbackWhere feedbackWhere) {
        Specification<FeedbackEntity> specification =
            (root,criteriaQuery,criteriaBuilder)->{
            Predicate predicates = criteriaBuilder.conjunction();
            if(StringUtils.hasText(feedbackWhere.getSearchValue())){
                if(feedbackWhere.getFieldNum() == 1){//userId以后会调app用户平台
                    predicates.getExpressions().add(criteriaBuilder.equal(root.get("userName"),feedbackWhere.getSearchValue()));
                }
                if(feedbackWhere.getFieldNum() == 2){
                    predicates.getExpressions().add(criteriaBuilder.equal(root.get("mobile"), feedbackWhere.getSearchValue()));
                }
            }
            if(feedbackWhere.getHandleStatus() == 1){
                predicates.getExpressions().add(criteriaBuilder.greaterThan(root.get("replyCount"),0));
                predicates.getExpressions().add(criteriaBuilder.or(criteriaBuilder.greaterThan(root.get("ignoreCount"),0)));
            }else{
                predicates.getExpressions().add(criteriaBuilder.equal(root.get("replyCount"),0));
                predicates.getExpressions().add(criteriaBuilder.equal(root.get("ignoreCount"),0));
            }
            return predicates;
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(feedbackWhere.getPage()-1, feedbackWhere.getPageSize(), sort);
        long count = feedbackRepository.count(specification);
        Page<FeedbackEntity> page = feedbackRepository.findAll(specification, pageable);
        List<FeedbackEntity> list = page.getContent();
        return new Object[]{count,list};
    }

}
