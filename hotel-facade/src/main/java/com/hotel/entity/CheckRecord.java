package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the check_record database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name="check_record")
@NamedQuery(name="CheckRecord.findAll", query="SELECT c FROM CheckRecord c")
public class CheckRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="check_in_time")
	private Date checkInTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="check_out_time")
	private Date checkOutTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="create_user_id")
	private Long createUserId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="over_time")
	private Date overTime;

	@Column(name="payed_charge")
	private Long payedCharge;

	@Column(name="payed_deposit")
	private Long payedDeposit;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="update_user_id")
	private Long updateUserId;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional many-to-one association to Room
	@ManyToOne(fetch=FetchType.LAZY)
	private Room room;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
}