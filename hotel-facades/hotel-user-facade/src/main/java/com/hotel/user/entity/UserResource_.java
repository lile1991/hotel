package com.hotel.user.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-20T20:13:12.909+0800")
@StaticMetamodel(UserResource.class)
public class UserResource_ {
	public static volatile SingularAttribute<UserResource, Long> id;
	public static volatile SingularAttribute<UserResource, Long> resourceId;
	public static volatile SingularAttribute<UserResource, Long> userId;

	public static volatile SingularAttribute<UserResource, Resource> resource;
	public static volatile SingularAttribute<UserResource, User> user;
}
