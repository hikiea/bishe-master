package com.lzy.bishe.modules.user.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * @author lizhongyi
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {

    private Integer id;

    private String username;

    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    private String status;
}
