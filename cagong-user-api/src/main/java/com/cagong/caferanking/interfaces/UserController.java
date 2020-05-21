package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.UserService;
import com.cagong.caferanking.domain.network.request.UserApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public String create(Model model, UserApiRequest resource) {
        userService.registerUser(resource);
        return "0";
    }

}
