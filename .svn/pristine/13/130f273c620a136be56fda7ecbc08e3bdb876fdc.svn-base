package com.pumex.tms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 15/06/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.TRAINING_CATEGORY)
public class TrainingCategory implements Serializable {

	private static final long serialVersionUID = -7002368989652924211L;

	public TrainingCategory() {
		super();
	}

	public TrainingCategory(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "category_id", columnDefinition = "bigint(15)")
	private long id;

	@Column(name = "parent_id", nullable = false, columnDefinition = "bigint(15) default '0'")
	public long parentId;

	@Column(name = "category", length = 50)
	private String category;

	@Column(name = "description", length = 100)
	private String description;

	@Column(name = "status", columnDefinition = "int(1) default '1'", nullable = false)
	private int status;
	
	@Column(name = "istrainingcategory", columnDefinition = "int(1) default '1'", nullable = false)
	private int istrainingcategory;
	
	@Column(name = "isforumcategory", columnDefinition = "int(1) default '1'", nullable = false)
	private int isforumcategory;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIstrainingcategory() {
		return istrainingcategory;
	}

	public void setIstrainingcategory(int istrainingcategory) {
		this.istrainingcategory = istrainingcategory;
	}

	public int getIsforumcategory() {
		return isforumcategory;
	}

	public void setIsforumcategory(int isforumcategory) {
		this.isforumcategory = isforumcategory;
	}
	
	

}
