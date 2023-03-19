package com.present.GifticBe.controller;


import com.present.GifticBe.domain.dto.UserJoinRequest;
import com.present.GifticBe.domain.dto.UserLoginRequest;
import com.present.GifticBe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto) {
        userService.join(dto.getEmail(), dto.getName(), dto.getNickname());
        return ResponseEntity.ok().body("회원가입이 성공하였습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto) {
        String token = userService.login(dto.getEmail(), dto.getToken());
        return ResponseEntity.ok().body(token);
    }
}
