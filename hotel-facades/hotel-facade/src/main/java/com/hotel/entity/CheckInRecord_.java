package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigInteger;
import java.util.Date;

@Generated(value="Dali", date="2017-12-20T20:13:12.328+0800")
@StaticMetamodel(CheckInRecord.class)
public class CheckInRecord_ {
	public static volatile SingularAttribute<CheckInRecord, String> id;
	public static volatile SingularAttribute<CheckInRecord, Date> checkInTime;
	public static volatile SingularAttribute<CheckInRecord, Date> createTime;
	public static volatile SingularAttribute<CheckInRecord, Date> overTime;
	public static volatile SingularAttribute<CheckInRecord, BigInteger> payedCharge;
	public static volatile SingularAttribute<CheckInRecord, BigInteger> payedDeposit;
	public static volatile SingularAttribute<CheckInRecord, String> state;
	public static volatile SingularAttribute<CheckInRecord, Date> updateTime;
	public static volatile ListAttribute<CheckInRecord, CheckInCustomer> checkInCustomers;
	public static volatile SingularAttribute<CheckInRecord, Member> member;
	public static volatile SingularAttribute<CheckInRecord, Room> room;
	public static volatile SingularAttribute<CheckInRecord, Long> roomId;
	public static volatile SingularAttribute<CheckInRecord, CheckOutRecord> checkOutRecord;
}
