package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the check_in_record database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name="check_in_record")
@NamedQuery(name="CheckInRecord.findAll", query="SELECT c FROM CheckInRecord c")
public class CheckInRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="check_in_time")
	private Date checkInTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="update_user_id")
	private User updateUser;

	//bi-directional many-to-one association to CheckInCustomer
	@OneToMany(mappedBy="checkInRecord")
	private List<CheckInCustomer> checkInCustomers;

	//bi-directional many-to-one association to Member
	@ManyToOne(fetch=FetchType.LAZY)
	private Member member;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="create_user_id")
	private User createUser;

	//bi-directional many-to-one association to Room
	@ManyToOne(fetch=FetchType.LAZY)
	private Room room;

	//bi-directional one-to-one association to CheckOutRecord
	@OneToOne(mappedBy="checkInRecord", fetch=FetchType.LAZY)
	private CheckOutRecord checkOutRecord;

	public CheckInRecord() {
	}

	public CheckInCustomer addCheckInCustomer(CheckInCustomer checkInCustomer) {
		if(checkInCustomers == null) {
			checkInCustomers = new ArrayList<>();
		}
		checkInCustomers.add(checkInCustomer);
		checkInCustomer.setCheckInRecord(this);

		return checkInCustomer;
	}

	public CheckInCustomer removeCheckInCustomer(CheckInCustomer checkInCustomer) {
		if(checkInCustomers == null) {
			return null;
		}
		checkInCustomers.remove(checkInCustomer);
		checkInCustomer.setCheckInRecord(null);

		return checkInCustomer;
	}

}