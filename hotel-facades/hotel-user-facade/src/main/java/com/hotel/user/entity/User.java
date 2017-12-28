package com.hotel.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Getter
@Setter
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String mobile;

	private String username;

	private String password;

	private String name;

	@Column(name = "update_user_id")
	private Long updateUserId;

	@Column(name = "create_user_id")
	private Long createUserId;

	private Boolean locked;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Transient
	List<Resource> resources;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="update_user_id", insertable = false, updatable = false)
	private User updateUser;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="create_user_id", insertable = false, updatable = false)
	private User createUser;

	public User() {
	}
}