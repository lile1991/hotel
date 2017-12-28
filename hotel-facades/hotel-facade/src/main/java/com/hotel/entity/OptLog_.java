package com.hotel.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated(value="Dali", date="2017-12-20T19:54:16.220+0800")
@StaticMetamodel(OptLog.class)
public class OptLog_ {
	public static volatile SingularAttribute<OptLog, Long> id;
	public static volatile SingularAttribute<OptLog, Date> createTime;
	public static volatile SingularAttribute<OptLog, String> optData;
	public static volatile SingularAttribute<OptLog, String> optLog;
	public static volatile SingularAttribute<OptLog, String> optType;
}
