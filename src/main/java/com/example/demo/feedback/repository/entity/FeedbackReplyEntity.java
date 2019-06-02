package com.example.demo.feedback.repository.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "feedback_reply", schema = "feedback")
public class FeedbackReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @Column(name = "reply_type")
    @ApiModelProperty(value = "回馈类型")
    private Integer replyType;

    @Column(name = "reply_context")
    @ApiModelProperty(value = "回复内容")
    private String replyContext;

    @Column(name = "feedback_id")
    @ApiModelProperty(value = "用户反馈id")
    private Integer feedbackId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public String getReplyContext() {
        return replyContext;
    }

    public void setReplyContext(String replyContext) {
        this.replyContext = replyContext;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }
}
