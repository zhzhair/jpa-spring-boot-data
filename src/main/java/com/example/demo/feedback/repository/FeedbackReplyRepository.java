package com.example.demo.feedback.repository;

import com.example.demo.feedback.repository.entity.FeedbackReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackReplyRepository extends JpaRepository<FeedbackReplyEntity,Integer> {
    List<FeedbackReplyEntity> findByFeedbackIdAndReplyType(int feedbackId, int replyType);
}
