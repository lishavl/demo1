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
@Table(name = AppConstants.TABLE_NAMES.MATERIAL_DOCUMENTS)
public class MaterialDocument implements Serializable {

	private static final long serialVersionUID = 8063208055308327559L;

	public MaterialDocument() {
		super();
	}

	public MaterialDocument(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "document_id", columnDefinition = "bigint(15)")
	private long id;

	@Column(name = "material_id", columnDefinition = "bigint(15)")
	private long materialId;

	@Column(name = "file_path", columnDefinition = "varchar(50)")
	private String filepath;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
