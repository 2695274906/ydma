package cn.itsqh.ydma.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Issue implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.headline
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private String headline;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.issue_context
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private String issueContext;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.publish_time
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private Date publishTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.answer_count
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private Integer answerCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.browse_count
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private Integer browseCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.video_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private Integer videoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.user_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column issue.right_answer_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    private Integer rightAnswerId;
    
    
    private List<Answer> answes;
    
    private Object user;
    

    public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	public List<Answer> getAnswes() {
		return answes;
	}

	public void setAnswes(List<Answer> answes) {
		this.answes = answes;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.id
     *
     * @return the value of issue.id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.id
     *
     * @param id the value for issue.id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.headline
     *
     * @return the value of issue.headline
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.headline
     *
     * @param headline the value for issue.headline
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setHeadline(String headline) {
        this.headline = headline == null ? null : headline.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.issue_context
     *
     * @return the value of issue.issue_context
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public String getIssueContext() {
        return issueContext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.issue_context
     *
     * @param issueContext the value for issue.issue_context
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setIssueContext(String issueContext) {
        this.issueContext = issueContext == null ? null : issueContext.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.publish_time
     *
     * @return the value of issue.publish_time
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.publish_time
     *
     * @param publishTime the value for issue.publish_time
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.answer_count
     *
     * @return the value of issue.answer_count
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public Integer getAnswerCount() {
        return answerCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.answer_count
     *
     * @param answerCount the value for issue.answer_count
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.browse_count
     *
     * @return the value of issue.browse_count
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public Integer getBrowseCount() {
        return browseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.browse_count
     *
     * @param browseCount the value for issue.browse_count
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.video_id
     *
     * @return the value of issue.video_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.video_id
     *
     * @param videoId the value for issue.video_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.user_id
     *
     * @return the value of issue.user_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.user_id
     *
     * @param userId the value for issue.user_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column issue.right_answer_id
     *
     * @return the value of issue.right_answer_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public Integer getRightAnswerId() {
        return rightAnswerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column issue.right_answer_id
     *
     * @param rightAnswerId the value for issue.right_answer_id
     *
     * @mbg.generated Wed Jul 10 15:20:11 CST 2019
     */
    public void setRightAnswerId(Integer rightAnswerId) {
        this.rightAnswerId = rightAnswerId;
    }
}