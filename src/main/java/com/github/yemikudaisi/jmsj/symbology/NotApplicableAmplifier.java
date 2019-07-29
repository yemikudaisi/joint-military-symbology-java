package com.github.yemikudaisi.jmsj.symbology;

public enum NotApplicableAmplifier implements Amplifier{
	Unspecified;

	@Override
	public String getSidcPart() {
		// TODO Auto-generated method stub
		return "00";
	}

}
