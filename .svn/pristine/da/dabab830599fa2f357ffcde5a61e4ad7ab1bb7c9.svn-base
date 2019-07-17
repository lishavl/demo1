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
@Table(name = AppConstants.TABLE_NAMES.SETTINGS)
public class Settings implements Serializable {

	private static final long serialVersionUID = 6459822034861413370L;

	public Settings() {
		super();
	}

	public Settings(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "settings_id", columnDefinition = "bigint(15)")
	private long id;

	@Column(name = "settings_type", columnDefinition = "bigint(2)")
	private int settingsType;

	@Column(name = "value", columnDefinition = "bigint(3)")
	private int value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSettingsType() {
		return settingsType;
	}

	public void setSettingsType(int settingsType) {
		this.settingsType = settingsType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
