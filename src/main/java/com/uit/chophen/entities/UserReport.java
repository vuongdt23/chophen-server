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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_report_id")
	private int userReportId;

	@Column(name="user_report_body")
	private String userReportBody;

	@Column(name="user_report_timestamp")
	private String userReportTimestamp;

	@Column(name="user_report_title")
	private String userReportTitle;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="user_report_creator")
	private UserProfile creator;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="user_report_target")
	private UserProfile target;

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

	public UserProfile getCreator() {
		return this.creator;
	}

	public void setCreator(UserProfile userProfile) {
		this.creator = userProfile;
	}

	public UserProfile getTarget() {
		return this.target;
	}

	public void setTarget(UserProfile userProfile) {
		this.target = userProfile;
	}

}