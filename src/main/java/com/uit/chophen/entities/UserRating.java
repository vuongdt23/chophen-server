package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_ratings database table.
 * 
 */
@Entity
@Table(name="user_ratings")
@NamedQuery(name="UserRating.findAll", query="SELECT u FROM UserRating u")
public class UserRating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_rating_id")
	private int userRatingId;

	@Column(name="user_rating_point")
	private int userRatingPoint;

	@Column(name="user_rating_timestamp")
	private String userRatingTimestamp;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="user_rating_creator")
	private UserProfile creator;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="user_rating_target")
	private UserProfile target;

	public UserRating() {
	}

	public int getUserRatingId() {
		return this.userRatingId;
	}

	public void setUserRatingId(int userRatingId) {
		this.userRatingId = userRatingId;
	}

	public int getUserRatingPoint() {
		return this.userRatingPoint;
	}

	public void setUserRatingPoint(int userRatingPoint) {
		this.userRatingPoint = userRatingPoint;
	}

	public String getUserRatingTimestamp() {
		return this.userRatingTimestamp;
	}

	public void setUserRatingTimestamp(String userRatingTimestamp) {
		this.userRatingTimestamp = userRatingTimestamp;
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