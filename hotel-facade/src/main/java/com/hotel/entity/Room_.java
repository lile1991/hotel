package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigInteger;
import java.util.Date;

@Generated(value="Dali", date="2017-12-20T20:13:12.899+0800")
@StaticMetamodel(Room.class)
public class Room_ {
	public static volatile SingularAttribute<Room, Long> id;
	public static volatile SingularAttribute<Room, String> alias;
	public static volatile SingularAttribute<Room, BigInteger> charge;
	public static volatile SingularAttribute<Room, Date> createTime;
	public static volatile SingularAttribute<Room, User> createUser;
	public static volatile SingularAttribute<Room, BigInteger> deposit;
	public static volatile SingularAttribute<Room, Integer> number;
	public static volatile SingularAttribute<Room, String> state;
	public static volatile SingularAttribute<Room, Date> updateTime;
	public static volatile SingularAttribute<Room, User> updateUser;
	public static volatile SingularAttribute<Room, RoomFloor> roomFloor;
	public static volatile SingularAttribute<Room, RoomType> roomType;
	public static volatile ListAttribute<Room, CheckInRecord> checkInRecords;
}
