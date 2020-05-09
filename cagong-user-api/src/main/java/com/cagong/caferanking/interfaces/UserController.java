package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.UserService;
import com.cagong.caferanking.domain.network.request.UserApiRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/users")
    public @ResponseBody String create(Model model, UserApiRequest resource) {
        userService.registerUser(resource);
        return "0";
    }

}
