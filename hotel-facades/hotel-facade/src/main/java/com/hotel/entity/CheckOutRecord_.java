package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigInteger;
import java.util.Date;

@Generated(value="Dali", date="2017-12-20T20:13:12.331+0800")
@StaticMetamodel(CheckOutRecord.class)
public class CheckOutRecord_ {
	public static volatile SingularAttribute<CheckOutRecord, String> id;
	public static volatile SingularAttribute<CheckOutRecord, Date> checkOutTime;
	public static volatile SingularAttribute<CheckOutRecord, Date> createTime;
	public static volatile SingularAttribute<CheckOutRecord, BigInteger> deductDeposit;
	public static volatile SingularAttribute<CheckOutRecord, String> remark;
	public static volatile SingularAttribute<CheckOutRecord, Date> updateTime;
	public static volatile SingularAttribute<CheckOutRecord, Long> updateUserId;
	public static volatile SingularAttribute<CheckOutRecord, CheckInRecord> checkInRecord;
	public static volatile SingularAttribute<CheckOutRecord, Long> createUserId;
}
