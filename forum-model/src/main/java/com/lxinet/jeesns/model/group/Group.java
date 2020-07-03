package com.lxinet.jeesns.model.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.model.member.Member;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zchuanzhao on 16/12/23.
 */
public class Group implements Serializable {
    private Integer id;
    private Date createTime;
    @NotBlank(message = "社团名称不能为空")
    private String name;
    private String type;
    private Integer count;
    private String teacher;
    private String teacherDes;
    private String userDes;
    private String prepare;
    private String design;
    private String logo;
    private Integer creator;
    private Member creatorMember;
    private String managers;
    private String tags;
    private String introduce;
    private Integer status;
    private Integer canPost;
    private Integer topicReview;
    private Integer topicCount;
    private Integer fansCount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherDes() {
        return teacherDes;
    }

    public void setTeacherDes(String teacherDes) {
        this.teacherDes = teacherDes;
    }

    public String getUserDes() {
        return userDes;
    }

    public void setUserDes(String userDes) {
        this.userDes = userDes;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getManagers() {
        return managers;
    }

    public void setManagers(String managers) {
        this.managers = managers;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Member getCreatorMember() {
        return creatorMember;
    }

    public void setCreatorMember(Member creatorMember) {
        this.creatorMember = creatorMember;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCanPost() {
        return canPost;
    }

    public void setCanPost(Integer canPost) {
        this.canPost = canPost;
    }

    public Integer getTopicReview() {
        return topicReview;
    }

    public void setTopicReview(Integer topicReview) {
        this.topicReview = topicReview;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }
}