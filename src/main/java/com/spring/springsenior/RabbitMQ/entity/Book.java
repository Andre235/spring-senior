package com.spring.springsenior.RabbitMQ.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/4
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private String author;
}
