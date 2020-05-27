package com.geek.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author : 赵静超
 * @date : 2020/5/26
 * @description :
 */

@Data
@Entity
@Table(name = "city")
@EntityListeners(AuditingEntityListener.class)
public class CityEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "govt_admin_area_cd")
    private String deptId;
    @Column(name = "parent_govt_admin_area_addr_cd")
    private String parentId;
    @Column(name = "govt_admin_area_nam")
    private String name;
}
