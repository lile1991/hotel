package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-11-24T19:05:44.946+0800")
@StaticMetamodel(CheckRecord.class)
public class CheckRecord_ {
	public static volatile SingularAttribute<CheckRecord, Long> id;
	public static volatile SingularAttribute<CheckRecord, Date> checkInTime;
	public static volatile SingularAttribute<CheckRecord, Date> checkOutTime;
	public static volatile SingularAttribute<CheckRecord, Date> createTime;
	public static volatile SingularAttribute<CheckRecord, Date> overTime;
	public static volatile SingularAttribute<CheckRecord, Long> payedCharge;
	public static volatile SingularAttribute<CheckRecord, Long> payedDeposit;
	public static volatile SingularAttribute<CheckRecord, String> state;
	public static volatile SingularAttribute<CheckRecord, Date> updateTime;
	public static volatile SingularAttribute<CheckRecord, User> user;
	public static volatile SingularAttribute<CheckRecord, Room> room;
	public static volatile SingularAttribute<CheckRecord, Long> createUserId;
	public static volatile SingularAttribute<CheckRecord, Long> updateUserId;
	public static volatile SingularAttribute<CheckRecord, Customer> customer;
	public static volatile ListAttribute<CheckRecord, CheckInCustomer> checkInCustomerList;
}
