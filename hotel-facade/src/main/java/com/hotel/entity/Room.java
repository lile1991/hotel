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

	@Column(name="create_user_id")
	private Long createUserId;

	private Long deposit;

	private int number;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="update_user_id")
	private Long updateUserId;

	//bi-directional many-to-one association to CheckRecord
	@OneToMany(mappedBy="room", fetch = FetchType.LAZY)
	private List<CheckRecord> checkRecordList;

	//bi-directional many-to-one association to RoomFloor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="floor")
	private RoomFloor roomFloor;

	//bi-directional many-to-one association to RoomType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type_id")
	private RoomType roomType;


	public CheckRecord addCheckRecordList(CheckRecord checkRecordList) {
		getCheckRecordList().add(checkRecordList);
		checkRecordList.setRoom(this);

		return checkRecordList;
	}

	public CheckRecord removeCheckRecordList(CheckRecord checkRecordList) {
		getCheckRecordList().remove(checkRecordList);
		checkRecordList.setRoom(null);

		return checkRecordList;
	}

}