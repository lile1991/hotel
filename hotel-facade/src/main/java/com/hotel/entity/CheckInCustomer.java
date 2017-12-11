package com.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 入住客户
 */
@Setter
@Getter
@Entity
@Table(name="check_in_customer")
@NamedQuery(name="CheckInCustomer.findAll", query="SELECT c FROM CheckInCustomer c")
public class CheckInCustomer {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time")
    private Date createTime;

    @Column(name="create_user_id")
    private Long createUserId;

    private String mobile;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="check_record_id")
    private CheckRecord checkRecord;
}
