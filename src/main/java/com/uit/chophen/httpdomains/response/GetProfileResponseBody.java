package com.uit.chophen.httpdomains.response;

import javax.persistence.Column;

public class GetProfileResponseBody {
	private int userId;

	private String userAddress;

	private String userEmail;

	private String userFullName;

	private String userPhone;

	private String userPic;
	
	private long likeCount;
	
	private long dislikeCount;
	private String accountName;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public GetProfileResponseBody(int userId, String userAddress, String userEmail, String userFullName,
			String userPhone, String userPic, long likeCount, long dislikeCount, String accountName) {
		super();
		this.userId = userId;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userFullName = userFullName;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
		this.accountName = accountName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

	public long getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(long dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
}
