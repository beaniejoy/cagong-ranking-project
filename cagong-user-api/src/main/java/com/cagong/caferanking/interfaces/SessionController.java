package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.UserService;
import com.cagong.caferanking.domain.network.request.SessionApiRequest;
import com.cagong.caferanking.domain.network.response.SessionApiResponse;
import com.cagong.caferanking.domain.network.response.UserApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SessionController {

    @Autowired
    private UserService userService;

    @PostMapping("/session")
    public @ResponseBody String create(HttpServletRequest request,
                  SessionApiRequest resource) {
        HttpSession session = request.getSession();
        UserApiResponse userApiResponse = userService.authenticate(resource.getEmail(), resource.getPassword());

        SessionApiResponse sessionApiResponse = SessionApiResponse.builder()
                .email(userApiResponse.getEmail())
                .account(userApiResponse.getAccount())
                .build();

        session.setAttribute("member", sessionApiResponse);
        session.setMaxInactiveInterval(30 * 60); // 30분 유효시간

        return "0";
    }
}
