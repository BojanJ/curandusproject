package com.model;

public class PatientsCascade {

	private Integer patientId;
	private String phone;
	private String firstName;
	private String middleInitial;
	private String lastName;
	//private Set<Providerpatient> providerpatients = new HashSet<Providerpatient>(0);
	private Integer activetreatmenId;

	public PatientsCascade() {
	}

	public PatientsCascade(Integer patientId, String phone, String firstName, String middleInitial,String lastName,
			Integer activetreatmenId) {
		this.patientId=patientId;
		this.phone = phone;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
	//	this.providerpatients = providerpatients;
		this.activetreatmenId = activetreatmenId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getActivetreatmenId() {
		return activetreatmenId;
	}

	public void setActivetreatmenId(Integer activetreatmenId) {
		this.activetreatmenId = activetreatmenId;
	}
}