package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the room_floor database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name="room_floor")
@NamedQuery(name="RoomFloor.findAll", query="SELECT r FROM RoomFloor r")
public class RoomFloor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer floor;

	private String alias;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="roomFloor")
	private List<Room> roomList;

	public RoomFloor() {
	}
}