package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.User;
import com.cagong.caferanking.domain.network.request.UserApiRequest;
import com.cagong.caferanking.domain.network.response.UserApiResponse;
import com.cagong.caferanking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserApiResponse registerUser(UserApiRequest resource) {

        Optional<User> existed = userRepository.findByEmail(resource.getEmail());

        if (existed.isPresent()) {
            throw new EmailExistedException(resource.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(resource.getPassword());

        User user = User.builder()
                .account(resource.getAccount())
                .email(resource.getEmail())
                .phoneNumber(resource.getPhoneNumber())
                .password(encodedPassword)
                .build();

        User saved = userRepository.save(user);

        return response(saved);
    }

    public UserApiResponse authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistedException(email));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordWrongException();
        }

        return response(user);
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
