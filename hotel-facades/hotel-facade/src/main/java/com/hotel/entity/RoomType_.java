package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigInteger;
import java.util.Date;

@Generated(value="Dali", date="2017-12-20T20:13:12.905+0800")
@StaticMetamodel(RoomType.class)
public class RoomType_ {
	public static volatile SingularAttribute<RoomType, Integer> id;
	public static volatile SingularAttribute<RoomType, BigInteger> charge;
	public static volatile SingularAttribute<RoomType, Date> createTime;
	public static volatile SingularAttribute<RoomType, Long> createUserId;
	public static volatile SingularAttribute<RoomType, BigInteger> deposit;
	public static volatile SingularAttribute<RoomType, String> icon;
	public static volatile SingularAttribute<RoomType, String> name;
	public static volatile SingularAttribute<RoomType, String> state;
	public static volatile SingularAttribute<RoomType, Date> updateTime;
	public static volatile SingularAttribute<RoomType, Long> updateUserId;
	public static volatile ListAttribute<RoomType, Room> rooms;
}
