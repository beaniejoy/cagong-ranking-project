package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.User;
import com.cagong.caferanking.domain.network.request.UserApiRequest;
import com.cagong.caferanking.domain.network.response.UserApiResponse;
import com.cagong.caferanking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserApiResponse create(UserApiRequest userApiRequest) {

        User user = User.builder()
                .account(userApiRequest.getAccount())
                .email(userApiRequest.getEmail())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .password(userApiRequest.getPassword())
                .build();

        User saved = userRepository.save(user);

        return response(saved);
    }

    public UserApiResponse response(User user) {

        return UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .build();
    }
}
