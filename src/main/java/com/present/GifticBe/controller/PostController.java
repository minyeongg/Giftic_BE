package com.present.GifticBe.controller;

import com.present.GifticBe.domain.dto.PostsAllResponseDto;
import com.present.GifticBe.domain.dto.PostsResponseDto;
import com.present.GifticBe.domain.dto.PostsSaveRequestDto;
import com.present.GifticBe.domain.dto.PostsUpdateRequestDto;
import com.present.GifticBe.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts")
    public List<PostsResponseDto> findAll() {
        return postsService.findAll();
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }



//    @GetMapping("/api/v1/posts")
//    public PostsAllResponseDto findAll() {
//        return postsService.findAll();
//    }
}
