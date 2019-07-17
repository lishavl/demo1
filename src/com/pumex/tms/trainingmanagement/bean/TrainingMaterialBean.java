/*
 * @Author Reshma Manoj
 */

package com.pumex.tms.trainingmanagement.bean;

public class TrainingMaterialBean {

	private long topicid;
	private String topic;
	private String description;
	private String reference;
	private String filepath;
	private long material_id;

	public TrainingMaterialBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrainingMaterialBean(long topicid, String topic) {
		super();
		this.topicid = topicid;
		this.topic = topic;
	}

	public TrainingMaterialBean(String topic, String description,
			String reference) {
		super();
		this.topic = topic;
		this.description = description;
		this.reference = reference;
	}

	public TrainingMaterialBean(long material_id, String topic,
			String filepath, String reference, String description) {
		super();
		this.material_id = material_id;
		this.topic = topic;
		this.description = description;
		this.reference = reference;
		this.filepath = filepath;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public long getTopicid() {
		return topicid;
	}

	public void setTopicid(long topicid) {
		this.topicid = topicid;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public long getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(long material_id) {
		this.material_id = material_id;
	}

	@Override
	public String toString() {
		return "TrainingMaterialBean [topicid=" + topicid + ", topic=" + topic
				+ ", description=" + description + ", reference=" + reference
				+ ", filepath=" + filepath + ", material_id=" + material_id
				+ "]";
	}

	

	
}
