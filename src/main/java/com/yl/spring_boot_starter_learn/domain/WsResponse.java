package com.yl.spring_boot_starter_learn.domain;

public class WsResponse {

	private String responseMsg;

	public WsResponse(String responseMsg) {
        this.responseMsg = responseMsg;
	}

	public String getResponseMsg() {
		return responseMsg;
	}
}
