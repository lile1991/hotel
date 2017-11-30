package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-11-24T19:05:44.761+0800")
@StaticMetamodel(OptLog.class)
public class OptLog_ {
	public static volatile SingularAttribute<OptLog, Long> id;
	public static volatile SingularAttribute<OptLog, Date> createTime;
	public static volatile SingularAttribute<OptLog, Long> createUserId;
	public static volatile SingularAttribute<OptLog, String> optData;
	public static volatile SingularAttribute<OptLog, String> optLog;
	public static volatile SingularAttribute<OptLog, String> optType;
}
