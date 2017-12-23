package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the check_out_record database table.
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="update_user_id")
	private User updateUser;
	@Column(name="update_user_id")
	private Long updateUserId;

	//bi-directional one-to-one association to CheckInRecord
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id", insertable = false, updatable = false)
	private CheckInRecord checkInRecord;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="create_user_id", insertable = false, updatable = false)
	private User createUser;
	@Column(name="create_user_id")
	private Long createUserId;

	public CheckOutRecord() {
	}
}