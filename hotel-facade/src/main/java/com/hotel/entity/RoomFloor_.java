package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-20T19:54:16.223+0800")
@StaticMetamodel(RoomFloor.class)
public class RoomFloor_ {
	public static volatile SingularAttribute<RoomFloor, Integer> floor;
	public static volatile SingularAttribute<RoomFloor, String> alias;
	public static volatile ListAttribute<RoomFloor, Room> roomList;
}
