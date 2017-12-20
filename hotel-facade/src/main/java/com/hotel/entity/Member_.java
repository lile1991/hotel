package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-12-20T20:13:12.335+0800")
@StaticMetamodel(Member.class)
public class Member_ {
	public static volatile SingularAttribute<Member, String> id;
	public static volatile SingularAttribute<Member, Date> createTime;
	public static volatile SingularAttribute<Member, User> createUser;
	public static volatile SingularAttribute<Member, String> idCard;
	public static volatile SingularAttribute<Member, String> mobile;
	public static volatile SingularAttribute<Member, String> name;
	public static volatile SingularAttribute<Member, String> state;
	public static volatile SingularAttribute<Member, Date> updateTime;
	public static volatile SingularAttribute<Member, User> updateUser;
	public static volatile ListAttribute<Member, CheckInRecord> checkInRecords;
}
