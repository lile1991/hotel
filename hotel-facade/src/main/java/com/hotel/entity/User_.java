package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-12-20T20:13:12.909+0800")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Date> createTime;
	public static volatile SingularAttribute<User, User> createUser;
	public static volatile SingularAttribute<User, String> mobile;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Date> updateTime;
	public static volatile SingularAttribute<User, User> updateUser;
	public static volatile ListAttribute<User, CheckInRecord> checkInRecords;
	public static volatile ListAttribute<User, CheckOutRecord> checkOutRecords;
}
