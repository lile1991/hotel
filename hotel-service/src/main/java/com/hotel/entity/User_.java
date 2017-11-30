package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-11-24T19:05:44.992+0800")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Date> createTime;
	public static volatile SingularAttribute<User, Long> createUserId;
	public static volatile SingularAttribute<User, String> mobile;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Date> updateTime;
	public static volatile SingularAttribute<User, Long> updateUserId;
	public static volatile ListAttribute<User, CheckRecord> checkRecordList;
}
