package com.example.demo.feedback.repository;

import com.example.demo.feedback.repository.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer>, JpaSpecificationExecutor<FeedbackEntity> {

    Optional<FeedbackEntity> findById(Integer feedbackId);

}
