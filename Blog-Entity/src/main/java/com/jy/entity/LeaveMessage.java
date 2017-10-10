package com.jy.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "blog_leave_message")
public class LeaveMessage extends BaseEntity {

  /**
   * 发送留言用户id
   * */
  @Column(name = "from_user_id")
  private Long fromUserId;

  /**
   * 接收留言用户id
   * */
  @Column(name = "to_user_id")
  private Long toUserId;

  /**
   * 内容
   * */
  @Column(name = "content")
  private String content;

  /**
   * 赞数量
   * */
  @Column(name = "like_count")
  private Integer likeCount;

  /**
   * 被回复的留言id
   * */
  @Column(name = "replied_leave_message_id")
  private Long repliedLeaveMessageId;

  /**
   * 相关回复
   * */
  @OrderBy(clause = "createTime asc")
  @Fetch(FetchMode.SUBSELECT)
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "repliedLeaveMessageId")
  private List<LeaveMessage> leaveMessageList;

  public Long getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(Long fromUserId) {
    this.fromUserId = fromUserId;
  }

  public Long getToUserId() {
    return toUserId;
  }

  public void setToUserId(Long toUserId) {
    this.toUserId = toUserId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }

  public Long getRepliedLeaveMessageId() {
    return repliedLeaveMessageId;
  }

  public void setRepliedLeaveMessageId(Long repliedLeaveMessageId) {
    this.repliedLeaveMessageId = repliedLeaveMessageId;
  }
}
