package com.present.GifticBe.service;

import com.present.GifticBe.domain.Posts;
import com.present.GifticBe.domain.dto.PostsResponseDto;
import com.present.GifticBe.domain.dto.PostsSaveRequestDto;
import com.present.GifticBe.domain.dto.PostsUpdateRequestDto;
import com.present.GifticBe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    public List<PostsResponseDto> findAll() {
        return PostsResponseDto.from(postRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        postRepository.delete(posts);
    }
}
