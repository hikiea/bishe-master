package com.example.demo.dto;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class AuthstrCheckDTO {
    Integer authstrId;
    String message;
    Boolean result;
}
