package com.model;

public class SubTreatment {

	private Integer subtreatmentid;
	private Integer activetreatmentid;
	
	public SubTreatment(){};
	
	public SubTreatment(Integer subtreatmentid, Integer activetreatmentid) {
		super();
		this.subtreatmentid = subtreatmentid;
		this.activetreatmentid = activetreatmentid;
	}

	public Integer getSubtreatmentid() {
		return subtreatmentid;
	}

	public void setSubtreatmentid(Integer subtreatmentid) {
		this.subtreatmentid = subtreatmentid;
	}

	public Integer getActivetreatmentid() {
		return activetreatmentid;
	}

	public void setActivetreatmentid(Integer activetreatmentid) {
		this.activetreatmentid = activetreatmentid;
	}
	
}

	
