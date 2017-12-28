package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the room_type database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name="room_type")
@NamedQuery(name="RoomType.findAll", query="SELECT r FROM RoomType r")
public class RoomType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private Long charge;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name = "create_user_id")
	private Long createUserId;

	private Long deposit;

	private String icon;

	private String name;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name = "update_user_id")
	private Long updateUserId;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="roomType")
	private List<Room> rooms;

	public RoomType() {
	}

}