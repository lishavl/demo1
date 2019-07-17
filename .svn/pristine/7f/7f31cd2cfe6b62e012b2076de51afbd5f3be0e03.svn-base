package com.pumex.tms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 22/06/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.ATTENDEE_TEST)
public class AttendeeTest implements Serializable {

	private static final long serialVersionUID = -2601299917888191396L;

	public AttendeeTest() {
		super();
	}

	public AttendeeTest(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition = "bigint(15)")
	private long id;

	@OneToOne
	@JoinColumn(name = "test_id", columnDefinition = "bigint(15)")
	private OnlineTest test;

	@OneToOne
	@JoinColumn(name = "attendee_id", columnDefinition = "bigint(15)")
	private UserDetails attendee;

	@Column(name = "status", columnDefinition = "int(1)")
	private int status;

	@Column(name = "remaining_seconds", columnDefinition = "int(7)")
	private int remainingSeconds;

	@Column(name = "mark", columnDefinition = "float(6) default 0.0")
	private float mark;

	@Column(name = "total_answered", columnDefinition = "int(3) default 0")
	private int totalAnswered;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OnlineTest getTest() {
		return test;
	}

	public void setTest(OnlineTest test) {
		this.test = test;
	}

	public UserDetails getAttendee() {
		return attendee;
	}

	public void setAttendee(UserDetails attendee) {
		this.attendee = attendee;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRemainingSeconds() {
		return remainingSeconds;
	}

	public void setRemainingSeconds(int remainingSeconds) {
		this.remainingSeconds = remainingSeconds;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public int getTotalAnswered() {
		return totalAnswered;
	}

	public void setTotalAnswered(int totalAnswered) {
		this.totalAnswered = totalAnswered;
	}

}
