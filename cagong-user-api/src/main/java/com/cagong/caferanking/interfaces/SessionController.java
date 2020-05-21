package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.UserService;
import com.cagong.caferanking.domain.network.request.SessionApiRequest;
import com.cagong.caferanking.domain.network.response.SessionApiResponse;
import com.cagong.caferanking.domain.network.response.UserApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class SessionController {

    private final UserService userService;

    @PostMapping("/session")
    public String create(HttpServletRequest request,
                  SessionApiRequest resource) {
        HttpSession session = request.getSession();
        UserApiResponse userApiResponse = userService.authenticate(resource.getEmail(), resource.getPassword());

        SessionApiResponse sessionApiResponse = SessionApiResponse.builder()
                .id(userApiResponse.getId())
                .email(userApiResponse.getEmail())
                .account(userApiResponse.getAccount())
                .build();

        session.setAttribute("member", sessionApiResponse);
        session.setMaxInactiveInterval(30 * 60); // 30분 유효시간

        return "0";
    }
}
