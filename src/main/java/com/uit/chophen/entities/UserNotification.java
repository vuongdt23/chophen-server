package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_notifications database table.
 * 
 */
@Entity
@Table(name="user_notifications")
@NamedQuery(name="UserNotification.findAll", query="SELECT u FROM UserNotification u")
public class UserNotification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_notification_id")
	private int userNotificationId;

	@Column(name="user_notification_body")
	private String userNotificationBody;

	@Column(name="user_notification_image")
	private String userNotificationImage;

	@Column(name="user_notification_timestamp")
	private String userNotificationTimestamp;

	@Column(name="user_notification_title")
	private String userNotificationTitle;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public UserNotification() {
	}

	public int getUserNotificationId() {
		return this.userNotificationId;
	}

	public void setUserNotificationId(int userNotificationId) {
		this.userNotificationId = userNotificationId;
	}

	public String getUserNotificationBody() {
		return this.userNotificationBody;
	}

	public void setUserNotificationBody(String userNotificationBody) {
		this.userNotificationBody = userNotificationBody;
	}

	public String getUserNotificationImage() {
		return this.userNotificationImage;
	}

	public void setUserNotificationImage(String userNotificationImage) {
		this.userNotificationImage = userNotificationImage;
	}

	public String getUserNotificationTimestamp() {
		return this.userNotificationTimestamp;
	}

	public void setUserNotificationTimestamp(String userNotificationTimestamp) {
		this.userNotificationTimestamp = userNotificationTimestamp;
	}

	public String getUserNotificationTitle() {
		return this.userNotificationTitle;
	}

	public void setUserNotificationTitle(String userNotificationTitle) {
		this.userNotificationTitle = userNotificationTitle;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}