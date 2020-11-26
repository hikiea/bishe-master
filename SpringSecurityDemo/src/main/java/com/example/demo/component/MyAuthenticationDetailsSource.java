package com.example.demo.component;

import javax.servlet.http.HttpServletRequest;

import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        MyWebAuthenticationDetails myWebAuthenticationDetails = null;
        try {
            myWebAuthenticationDetails = new MyWebAuthenticationDetails(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myWebAuthenticationDetails;
    }
}