package com.shiftapp.ws.model.enums;

public enum CountryCodeEnum {
	ISR ("+972"),
	US ("+1");
	
	private final String code;
	
	private CountryCodeEnum(String code) {
		this.code = code;
	}
	
	public static CountryCodeEnum getInstance(String code) {
		switch (code) {
		case "+972":
			return ISR;
		case "+1":
			return US;
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		return code;
	}
}
