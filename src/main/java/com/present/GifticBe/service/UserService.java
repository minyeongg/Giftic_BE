package com.present.GifticBe.service;

import com.present.GifticBe.domain.Users;
import com.present.GifticBe.exception.AppException;
import com.present.GifticBe.exception.ErrorCode;
import com.present.GifticBe.repository.UserRepository;
import com.present.GifticBe.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;

    private Long expireTimeMs = 1000 * 60 * 60L;

    public String join(String email, String password) {

        // username 중복 체크
        userRepository.findByEmail(email)
                .ifPresent(users -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, email + "는 이미 있습니다.");
                });

        // 저장
        Users users = Users.builder()
                .email(email)
                .password(encoder.encode(password))
                .build();

        userRepository.save(users);

        return "SUCCESS";
    }

    public String login(String email, String password) {

        /**
         * userName 없음
         */
        Users selectedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));

        /**
         * password 틀림
         */
        log.info("selectedPw:{} pw:{}", selectedUser.getPassword(), password);
        if(!encoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 했습니다.");
        }

        /**
         * 앞에서 Exception 나지 않았으면 토큰 발행 및 반환
         */
        String token = JwtTokenUtil.createToken(selectedUser.getEmail(), key, expireTimeMs);

        return token;
    }
}
