package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-11-24T19:05:44.970+0800")
@StaticMetamodel(Customer.class)
public class Customer_ {
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, Date> createTime;
	public static volatile SingularAttribute<Customer, String> hotel;
	public static volatile SingularAttribute<Customer, String> idCard;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, String> state;
	public static volatile SingularAttribute<Customer, Date> updateTime;
	public static volatile SingularAttribute<Customer, CheckRecord> checkRecord;
	public static volatile SingularAttribute<Customer, Long> createUserId;
	public static volatile SingularAttribute<Customer, Long> updateUserId;
	public static volatile ListAttribute<Customer, CheckRecord> checkRecordList;
}
