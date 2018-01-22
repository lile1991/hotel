package com.hotel.room.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-12-20T20:13:12.864+0800")
@StaticMetamodel(CheckInCustomer.class)
public class CheckInCustomer_ {
	public static volatile SingularAttribute<CheckInCustomer, String> id;
	public static volatile SingularAttribute<CheckInCustomer, Date> createTime;
	public static volatile SingularAttribute<CheckInCustomer, String> idCard;
	public static volatile SingularAttribute<CheckInCustomer, String> mobile;
	public static volatile SingularAttribute<CheckInCustomer, String> name;
	public static volatile SingularAttribute<CheckInCustomer, String> state;
	public static volatile SingularAttribute<CheckInCustomer, CheckInRecord> checkInRecord;
}
