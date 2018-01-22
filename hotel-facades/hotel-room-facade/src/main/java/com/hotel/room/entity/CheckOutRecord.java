package com.hotel.room.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 离店记录， 与CheckInRecord一对一关联
 * 
 */
@Getter
@Setter
@Entity
@Table(name="check_out_record")
@NamedQuery(name="CheckOutRecord.findAll", query="SELECT c FROM CheckOutRecord c")
public class CheckOutRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="check_out_time")
	private Date checkOutTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name = "deduct_deposit")
	private Long deductDeposit;

	private String remark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="update_user_id")
	private Long updateUserId;

	//bi-directional one-to-one association to CheckInRecord
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id", insertable = false, updatable = false)
	private CheckInRecord checkInRecord;

	@Column(name="create_user_id")
	private Long createUserId;

	public CheckOutRecord() {
	}
}