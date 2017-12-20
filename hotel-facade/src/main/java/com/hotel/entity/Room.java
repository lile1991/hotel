package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the room database table.
 * 
 */
@Getter
@Setter
@Entity
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String alias;

	private Long charge;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="create_user_id")
	private User createUser;

	private Long deposit;

	private int number;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="update_user_id")
	private User updateUser;

	//bi-directional many-to-one association to RoomFloor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="floor")
	private RoomFloor roomFloor;

	//bi-directional many-to-one association to RoomType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type")
	private RoomType roomType;

	//bi-directional many-to-one association to CheckInRecord
	@OneToMany(mappedBy="room")
	private List<CheckInRecord> checkInRecords;

	public Room() {
	}

	public void setCheckInRecords(List<CheckInRecord> checkInRecords) {
		this.checkInRecords = checkInRecords;
	}

	public CheckInRecord addCheckInRecord(CheckInRecord checkInRecord) {
		getCheckInRecords().add(checkInRecord);
		checkInRecord.setRoom(this);

		return checkInRecord;
	}

	public CheckInRecord removeCheckInRecord(CheckInRecord checkInRecord) {
		getCheckInRecords().remove(checkInRecord);
		checkInRecord.setRoom(null);

		return checkInRecord;
	}

}