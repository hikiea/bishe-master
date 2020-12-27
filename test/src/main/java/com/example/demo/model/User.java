package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Lzy
 */
@Data
public class User {
    private int id;
    private String user;
    private String password;
    private LocalDateTime date;
}
