package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.UserService;
import com.cagong.caferanking.domain.network.request.UserApiRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(value = "/users")
    public String create(UserApiRequest userApiRequest) {

        userService.create(userApiRequest);
        return "member/regst_process";
    }

}
