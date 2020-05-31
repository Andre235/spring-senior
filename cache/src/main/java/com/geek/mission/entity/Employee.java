package com.geek.mission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/2
 * @description : 雇员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@EntityListeners(AuditingEntityListener.class)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    /**
     * 性别 1:男 0:女
     */
    @Column(name = "gender")
    private Integer gender;
    /**
     * 部门id
     */
    @Column(name = "d_id")
    private Integer dId;

}
