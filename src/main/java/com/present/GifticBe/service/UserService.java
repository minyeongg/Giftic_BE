package com.present.GifticBe.service;

import com.present.GifticBe.domain.User;
import com.present.GifticBe.exception.ErrorCode;
import com.present.GifticBe.exception.LoginException;
import com.present.GifticBe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder encoder;

    private Long expireTimeMs = 1000 * 60 * 60L;

    public String join(String email, String nickname, String name) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new LoginException(ErrorCode.USERNAME_DUPLICATED, email + "는 이미 존재합니다.");
                });

        User user = User.builder()
                .name(email)
                .nickname(nickname)
                .name(name)
                .build();

        userRepository.save(user);

        return "SUCCESS";
    }

    public String login(String email, String token) {

        /**
         * email 없음
         */
        User selectedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginException(ErrorCode.USERNAME_NOT_FOUND, email + "은 존재하지 않는 이메일입니다."));

        /**
         * 패스워드 검증
         */

        /**
         * 앞에서 예외 발생하지 않았으면 토큰 발행 및 반환
         */

//        String token = JwtTokenUtil

        return token;
    }
}
