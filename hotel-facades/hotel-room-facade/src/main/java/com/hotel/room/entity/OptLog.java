package com.hotel.room.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 操作日志
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

	@Column(name = "create_user_id")
	private Long createUserId;

	@Column(name="opt_data")
	private String optData;

	@Column(name="opt_log")
	private String optLog;

	@Column(name="opt_type")
	private String optType;

	public OptLog() {
	}

}