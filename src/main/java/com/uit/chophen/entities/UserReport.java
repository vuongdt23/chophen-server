package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_reports database table.
 * 
 */
@Entity
@Table(name="user_reports")
@NamedQuery(name="UserReport.findAll", query="SELECT u FROM UserReport u")
public class UserReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_report_id")
	private int userReportId;

	@Column(name="user_report_body")
	private String userReportBody;

	@Column(name="user_report_timestamp")
	private String userReportTimestamp;

	@Column(name="user_report_title")
	private String userReportTitle;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_report_creator")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_report_target")
	private User user2;

	public UserReport() {
	}

	public int getUserReportId() {
		return this.userReportId;
	}

	public void setUserReportId(int userReportId) {
		this.userReportId = userReportId;
	}

	public String getUserReportBody() {
		return this.userReportBody;
	}

	public void setUserReportBody(String userReportBody) {
		this.userReportBody = userReportBody;
	}

	public String getUserReportTimestamp() {
		return this.userReportTimestamp;
	}

	public void setUserReportTimestamp(String userReportTimestamp) {
		this.userReportTimestamp = userReportTimestamp;
	}

	public String getUserReportTitle() {
		return this.userReportTitle;
	}

	public void setUserReportTitle(String userReportTitle) {
		this.userReportTitle = userReportTitle;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}