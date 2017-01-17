package com.model;

import java.util.Date;

public class TreatmentItem {
	
	private Integer treatmentItemId;
	private Integer treatmentID;
	private String name;
	private String typeT;
	private String repeatT;
	private String duration;
	private String renderingInfo;
	private Date created;
	private Integer createdBy;
	private Date modified;
	private Integer modifiedBy;

	public TreatmentItem() {
	}

	public TreatmentItem(Integer treatmentItemId, Integer treatmentID, String name, String typeT, String repeatT, String duration,
			String renderingInfo, Date created, Integer createdBy, Date modified, Integer modifiedBy) {
		this.treatmentItemId=treatmentItemId;
		this.treatmentID = treatmentID;
		this.name = name;
		this.typeT = typeT;
		this.repeatT = repeatT;
		this.duration = duration;
		this.renderingInfo = renderingInfo;
		this.created = created;
		this.createdBy = createdBy;
		this.modified = modified;
		this.modifiedBy = modifiedBy;
	}

	public Integer getTreatmentItemId() {
		return treatmentItemId;
	}

	public void setTreatmentItemId(Integer treatmentItemId) {
		this.treatmentItemId = treatmentItemId;
	}

	public Integer getTreatmentID() {
		return treatmentID;
	}

	public void setTreatmentID(Integer treatmentID) {
		this.treatmentID = treatmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeT() {
		return typeT;
	}

	public void setTypeT(String typeT) {
		this.typeT = typeT;
	}

	public String getRepeatT() {
		return repeatT;
	}

	public void setRepeatT(String repeatT) {
		this.repeatT = repeatT;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRenderingInfo() {
		return renderingInfo;
	}

	public void setRenderingInfo(String renderingInfo) {
		this.renderingInfo = renderingInfo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
