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
@Table(name = AppConstants.TABLE_NAMES.TRAINING_MATERIALS)
public class TrainingMaterial implements Serializable {

	private static final long serialVersionUID = 8335351703002555671L;

	public TrainingMaterial() {
		super();
	}

	public TrainingMaterial(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "material_id")
	private long id;
	
	@Column(name = "title", length=100)
	private String title;

	@Column(name = "description", length=1000)
	private String description;

	@Column(name = "reference", length=300)
	private String reference;

	@Column(name = "keyword", length=100)
	private String keyword;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
