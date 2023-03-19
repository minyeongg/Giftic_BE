package com.present.GifticBe.service;

import com.present.GifticBe.domain.Posts;
import com.present.GifticBe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Posts getPosts(Long id) {
        Optional<Posts> posts = this.postRepository.findById(id);
        if(posts.isPresent()) {
            return posts.get();
        } else {
            throw new RuntimeException();
        }
    }

    public void create(String title, String content, Integer writer_id, LocalDateTime createDate, Integer likes_id_) {
        Posts posts = new Posts();
        posts.setTitle(title);
        posts.setContent(content);
        posts.setWriter_id(writer_id);
        posts.setCreateDate(createDate);
        posts.setLikes_id(likes_id_);

        this.postRepository.save(posts);
    }

    public void delete(Posts posts) {
        this.postRepository.delete(posts);
    }

    public void modify(Posts posts, String title, String content, LocalDateTime modifyDate) {
        posts.setTitle(title);
        posts.setContent(content);
        posts.setModifyDate(modifyDate);
        this.postRepository.save(posts);
    }
}
