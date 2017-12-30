package com.hotel.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 资源权限关联
 */
@Getter
@Setter
@Entity
@NamedQuery(name="UserResource.findAll", query="SELECT u FROM UserResource u")
public class UserResource implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="resource_id", insertable = false, updatable = false)
    private Resource resource;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

}
