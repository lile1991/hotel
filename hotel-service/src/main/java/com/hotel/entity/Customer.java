package com.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="create_user_id")
	private Long createUserId;

	private String hotel;

	@Column(name="id_card")
	private String idCard;

	private String name;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="update_user_id")
	private Long updateUserId;

	//bi-directional many-to-one association to CheckRecord
	@OneToMany(mappedBy="customer")
	private List<CheckRecord> checkRecordList;

	public CheckRecord addCheckRecordList(CheckRecord checkRecordList) {
		getCheckRecordList().add(checkRecordList);
		checkRecordList.setCustomer(this);

		return checkRecordList;
	}

	public CheckRecord removeCheckRecordList(CheckRecord checkRecordList) {
		getCheckRecordList().remove(checkRecordList);
		checkRecordList.setCustomer(null);

		return checkRecordList;
	}

}