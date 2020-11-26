package com.example.demo.component;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MyWebAuthenticationDetails extends WebAuthenticationDetails {
    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */

    private static final long serialVersionUID = 6975601077710753878L;

    private String username;

    private String password;

    private String validCode;

    private String sessionCodeValue;

    private long sessionCodeTime;

    public MyWebAuthenticationDetails(HttpServletRequest request) throws IOException {
        super(request);

        String temp;
        StringBuilder s = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(request.getReader());
            while ((temp = bufferedReader.readLine()) != null) {
                s = s.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map map = JSON.parseObject(String.valueOf(s), Map.class);

        username = (String) map.get("username");
        password = (String) map.get("password");
        validCode = (String) map.get("validCode");

//        下面代码用于伪装验证码生成
//        String codeValue = "123";
//        Long codeTime = Long.valueOf(123);
//        request.getSession().setAttribute("codeValue", codeValue);
//        request.getSession().setAttribute("codeTime", codeTime);

        sessionCodeValue = (String) request.getSession().getAttribute("codeValue");
        sessionCodeTime = (Long) request.getSession().getAttribute("codeTime");
    }
}
