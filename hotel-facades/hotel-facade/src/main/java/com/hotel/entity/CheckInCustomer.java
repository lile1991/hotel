package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the check_in_customer database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name="check_in_customer")
@NamedQuery(name="CheckInCustomer.findAll", query="SELECT c FROM CheckInCustomer c")
public class CheckInCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name = "create_user_id")
	private Long createUserId;

	@Column(name="id_card")
	private String idCard;

	private String mobile;

	private String name;

	private String state;

	//bi-directional many-to-one association to CheckInRecord
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="check_in_id", insertable = false, updatable = false)
	private CheckInRecord checkInRecord;

	//bi-directional many-to-one association to CheckInRecord
	@Column(name = "check_in_id")
	private Long checkInRecordId;

	public CheckInCustomer() {
	}

}