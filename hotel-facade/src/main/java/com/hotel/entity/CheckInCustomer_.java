package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-11-24T19:05:44.970+0800")
@StaticMetamodel(CheckInCustomer.class)
public class CheckInCustomer_ {
	public static volatile SingularAttribute<CheckInCustomer, Long> id;
	public static volatile SingularAttribute<CheckInCustomer, Date> createTime;
	public static volatile SingularAttribute<CheckInCustomer, String> mobile;
	public static volatile SingularAttribute<CheckInCustomer, String> idCard;
	public static volatile SingularAttribute<CheckInCustomer, String> name;
	public static volatile SingularAttribute<CheckInCustomer, String> state;
	public static volatile SingularAttribute<CheckInCustomer, Date> updateTime;
	public static volatile SingularAttribute<CheckInCustomer, CheckRecord> checkRecord;
	public static volatile SingularAttribute<CheckInCustomer, Long> createUserId;
	public static volatile SingularAttribute<CheckInCustomer, Long> updateUserId;
}
