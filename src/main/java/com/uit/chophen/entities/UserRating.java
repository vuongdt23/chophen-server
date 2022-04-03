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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_rating_id")
	private int userRatingId;

	@Column(name="user_rating_point")
	private int userRatingPoint;

	@Column(name="user_rating_timestamp")
	private String userRatingTimestamp;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_rating_creator")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_rating_target")
	private User user2;

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