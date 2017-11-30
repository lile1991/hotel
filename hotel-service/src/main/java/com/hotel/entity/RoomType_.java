package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-11-24T19:05:44.987+0800")
@StaticMetamodel(RoomType.class)
public class RoomType_ {
	public static volatile SingularAttribute<RoomType, Integer> id;
	public static volatile SingularAttribute<RoomType, Long> charge;
	public static volatile SingularAttribute<RoomType, Date> createTime;
	public static volatile SingularAttribute<RoomType, Long> createUserId;
	public static volatile SingularAttribute<RoomType, Long> deposit;
	public static volatile SingularAttribute<RoomType, String> icon;
	public static volatile SingularAttribute<RoomType, String> name;
	public static volatile SingularAttribute<RoomType, String> state;
	public static volatile SingularAttribute<RoomType, Date> updateTime;
	public static volatile SingularAttribute<RoomType, Room> room;
	public static volatile SingularAttribute<RoomType, Long> updateUserId;
	public static volatile ListAttribute<RoomType, Room> roomList;
}
