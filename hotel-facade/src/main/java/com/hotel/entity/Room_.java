package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-11-24T19:05:44.979+0800")
@StaticMetamodel(Room.class)
public class Room_ {
	public static volatile SingularAttribute<Room, Long> id;
	public static volatile SingularAttribute<Room, String> alias;
	public static volatile SingularAttribute<Room, Long> charge;
	public static volatile SingularAttribute<Room, Date> createTime;
	public static volatile SingularAttribute<Room, Long> createUserId;
	public static volatile SingularAttribute<Room, Long> deposit;
	public static volatile SingularAttribute<Room, Integer> number;
	public static volatile SingularAttribute<Room, String> state;
	public static volatile SingularAttribute<Room, Date> updateTime;
	public static volatile SingularAttribute<Room, RoomFloor> roomFloor;
	public static volatile SingularAttribute<Room, Long> updateUserId;
	public static volatile ListAttribute<Room, CheckRecord> checkRecordList;
	public static volatile SingularAttribute<Room, RoomType> roomType;
}
