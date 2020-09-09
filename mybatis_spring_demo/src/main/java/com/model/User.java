package com.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qinglin
 * @create 2020-09-06 12:50
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;

}
