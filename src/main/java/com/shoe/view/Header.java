
package com.shoe.view;

public class Header {
	private int flag;
	private int errorCode;
	private String errorDesc;

	public Header(int flag, int errorCode, String errorDesc) {
		this.flag = flag;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public Header() {
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
