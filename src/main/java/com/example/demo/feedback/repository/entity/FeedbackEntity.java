package com.example.demo.feedback.repository.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "feedback", schema = "demo", catalog = "")
public class FeedbackEntity {
    @ApiModelProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(value = "用户id")
    @Column(name = "user_id")
    private int userId;

    @ApiModelProperty(value = "手机号")
    @Column(name = "mobile")
    private String mobile;

    @ApiModelProperty(value = "姓名")
    @Column(name = "user_name")
    private String userName;

    @ApiModelProperty(value = "app版本")
    @Column(name = "app_version")
    private String appVersion;

    @ApiModelProperty(value = "系统版本")
    @Column(name = "sys_version")
    private String sysVersion;

    @ApiModelProperty(value = "反馈内容")
    @Column(name = "content")
    private String content;

    @ApiModelProperty(value = "反馈时间")
    @Column(name = "create_time")
    @JSONField(serialize = false)
    private Timestamp createTime;

    @ApiModelProperty(value = "手机操作系统类型状态")
    @Column(name = "os_type")
    private String osType;

    @ApiModelProperty(value = "网络状态")
    @Column(name = "online_status")
    private String onlineStatus;

    @ApiModelProperty(value = "回复将次数")
    @Column(name = "reply_count")
    private Integer replyCount;

    @ApiModelProperty(value = "忽略次数")
    @Column(name = "ignore_count")
    private Integer ignoreCount;

    @Override
    public String toString() {
        return "FeedbackEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                ", osType='" + osType + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", sysVersion='" + sysVersion + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", osType='" + osType + '\'' +
                ", onlineStatus='" + onlineStatus + '\'' +
                ", replyCount=" + replyCount +
                ", ignoreCount=" + ignoreCount +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getIgnoreCount() {
        return ignoreCount;
    }

    public void setIgnoreCount(Integer ignoreCount) {
        this.ignoreCount = ignoreCount;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
