package com.hotel.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 受保护的资源
 */
@Getter
@Setter
@Entity
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String type;

    private String module;

    private String name;

    private String value;

    private String remark;

    @Column(name = "parent_id")
    private Long parentId;

    /*public String getGroupAndValue() {
        return group + ":" + value;
    }*/
}
