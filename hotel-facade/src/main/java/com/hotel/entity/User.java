package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 管理人员
 */
@Getter
@Setter
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="create_user_id")
	private Long createUserId;

	private String mobile;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="update_user_id")
	private Long updateUserId;

	//bi-directional many-to-one association to CheckRecord
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<CheckRecord> checkRecordList;

}