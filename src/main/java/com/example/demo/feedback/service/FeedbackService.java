package com.example.demo.feedback.service;

import com.example.demo.feedback.dto.request.FeedbackInfo;
import com.example.demo.feedback.dto.request.FeedbackWhere;

public interface FeedbackService {
    /**
     *  上传反馈数据
     */
    void uploadFeedback(FeedbackInfo feedbackInfo, int userId);
    /**
     *  保存回馈信息
     */
    void saveFeedbackInfo(int replyType, int id, String context);

    /**
     *  保存回馈信息
     */
    boolean[] canFeedback(int id);

    /**
     * 获取反馈详情的数据
     */
    Object[] getFeedbackDetail(FeedbackWhere feedbackWhere);

}
