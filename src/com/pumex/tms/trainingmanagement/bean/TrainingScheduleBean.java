/*
 * @Author Reshma Manoj
 */
package com.pumex.tms.trainingmanagement.bean;

import java.util.List;
import com.pumex.tms.models.TopicAttendee;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.usermanagement.bean.Register;

public class TrainingScheduleBean {

	private long id;
	private String topic;
	private String description;
	public TrainingCategory category;
	private String startDateTime;
	private String endDateTime;
	private int status;
	private String maincategory;
	private String subCategory;
	private String categoryName;
	private String subCategoryName;
	private long[] trainerlist;
	private long[] attendeelist;

	private List<Register> trainerdetails;
	private List<Register> attendeedetails;

	public TrainingScheduleBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrainingScheduleBean(long id, String topic) {
		super();
		this.id = id;
		this.topic = topic;
	}

	public TrainingScheduleBean(long id, String topic, String description,
			TrainingCategory category, int status, String maincategory,
			String subCategory, String categoryName, String subCategoryName) {
		super();
		this.id = id;
		this.topic = topic;
		this.description = description;
		this.category = category;
		this.status = status;
		this.maincategory = maincategory;
		this.subCategory = subCategory;
		this.categoryName = categoryName;
		this.subCategoryName = subCategoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public List<Register> getTrainerdetails() {
		return trainerdetails;
	}

	public void setTrainerdetails(List<Register> trainerdetails) {
		this.trainerdetails = trainerdetails;
	}

	public List<Register> getAttendeedetails() {
		return attendeedetails;
	}

	public void setAttendeedetails(List<Register> attendeedetails) {
		this.attendeedetails = attendeedetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
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

	public TrainingCategory getCategory() {
		return category;
	}

	public void setCategory(TrainingCategory category) {
		this.category = category;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMaincategory() {
		return maincategory;
	}

	public void setMaincategory(String maincategory) {
		this.maincategory = maincategory;
	}

	public long[] getTrainerlist() {
		return trainerlist;
	}

	public void setTrainerlist(long[] trainerlist) {
		this.trainerlist = trainerlist;
	}

	public long[] getAttendeelist() {
		return attendeelist;
	}

	public void setAttendeelist(long[] attendeelist) {
		this.attendeelist = attendeelist;
	}

	@Override
	public String toString() {
		return "TrainingScheduleBean [id=" + id + ", topic=" + topic
				+ ", description=" + description + ", category=" + category
				+ ", startDateTime=" + ", status=" + status + ", maincategory="
				+ maincategory + ", subCategory=" + subCategory + "]";
	}

}
