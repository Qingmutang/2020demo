package com.example.demo0905.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinglin
 * @create 2020-09-05 09:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    /**
     * 姓名
     */
    private String name;

    /**
     * 学号
     */
    private String code;

    /**
     * 年龄
     */
    private Integer age;
}
