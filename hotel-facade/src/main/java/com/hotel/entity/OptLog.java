package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the opt_log database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name="opt_log")
@NamedQuery(name="OptLog.findAll", query="SELECT o FROM OptLog o")
public class OptLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="create_user_id")
	private User createUser;

	@Column(name="opt_data")
	private String optData;

	@Column(name="opt_log")
	private String optLog;

	@Column(name="opt_type")
	private String optType;

	public OptLog() {
	}

}