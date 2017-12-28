package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the member database table.
 * 
 */
@Getter
@Setter
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name = "create_user_id")
	private Long createUserId;

	@Column(name="id_card")
	private String idCard;

	private String mobile;

	private String name;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name = "update_user_id")
	private Long updateUserId;

	//bi-directional many-to-one association to CheckInRecord
	@OneToMany(mappedBy="member")
	private List<CheckInRecord> checkInRecords;

	public Member() {
	}

	public CheckInRecord addCheckInRecord(CheckInRecord checkInRecord) {
		getCheckInRecords().add(checkInRecord);
		checkInRecord.setMember(this);

		return checkInRecord;
	}

	public CheckInRecord removeCheckInRecord(CheckInRecord checkInRecord) {
		getCheckInRecords().remove(checkInRecord);
		checkInRecord.setMember(null);

		return checkInRecord;
	}

}