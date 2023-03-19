package com.present.GifticBe.controller;

import com.present.GifticBe.domain.Posts;
import com.present.GifticBe.repository.PostRepository;
import com.present.GifticBe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/posts")
@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping("/create")
    public ResponseEntity<String> postsCreate(Posts posts) {
        this.postService.create(posts.getTitle(), posts.getContent(), posts.getWriter_id(), posts.getCreateDate(), posts.getLikes_id());

        return ResponseEntity.ok().body("Post 저장");
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<String> postList(@PathVariable Long id) {
        Optional<Posts> posts = this.postRepository.findById(id);
        return ResponseEntity.ok().body(posts.toString());
    }
}
