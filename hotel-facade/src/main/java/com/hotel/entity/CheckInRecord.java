package com.hotel.entity;

import com.hotel.enums.CheckStateEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

	private String mobile;

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

	public String getStateDesc() {
		return CheckStateEnum.valueOf(state).getDesc();
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="update_user_id", insertable = false, updatable = false)
	private User updateUser;
	@Column(name = "update_user_id")
	private Long updateUserId;

	//bi-directional many-to-one association to CheckInCustomer
	@OneToMany(mappedBy="checkInRecord")
	private List<CheckInCustomer> checkInCustomers;

	//bi-directional many-to-one association to Member
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "member_id", insertable = false, updatable = false)
	private Member member;
	@Column(name = "member_id")
	private Long memberId;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="create_user_id", insertable = false, updatable = false)
	private User createUser;
	@Column(name = "create_user_id")
	private Long createUserId;

	//bi-directional many-to-one association to Room
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "room_id", insertable = false, updatable = false)
	private Room room;
	@Column(name = "room_id")
	private Long roomId;

	//bi-directional one-to-one association to CheckOutRecord
	@OneToOne(mappedBy="checkInRecord", fetch=FetchType.LAZY)
	@JoinColumn(name = "id", insertable = false, updatable = false)
	private CheckOutRecord checkOutRecord;

	public CheckInRecord() {
	}
}