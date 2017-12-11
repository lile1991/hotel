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
@Setter
@Getter
@Entity
@Table(name="room_floor")
@NamedQuery(name="RoomFloor.findAll", query="SELECT r FROM RoomFloor r")
public class RoomFloor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer floor;

	private String alias;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy="roomFloor", fetch = FetchType.LAZY)
	private List<Room> roomList;

	public Room addRoomList(Room roomList) {
		getRoomList().add(roomList);
		roomList.setRoomFloor(this);

		return roomList;
	}

	public Room removeRoomList(Room roomList) {
		getRoomList().remove(roomList);
		roomList.setRoomFloor(null);

		return roomList;
	}

}