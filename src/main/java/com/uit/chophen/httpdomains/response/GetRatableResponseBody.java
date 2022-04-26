package com.uit.chophen.httpdomains.response;

public class GetRatableResponseBody {
	private boolean ratable;

	public boolean isRatable() {
		return ratable;
	}

	public void setRatable(boolean ratable) {
		this.ratable = ratable;
	}

	public GetRatableResponseBody(boolean ratable) {
		super();
		this.ratable = ratable;
	}
}


