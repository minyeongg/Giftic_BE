package com.present.GifticBe.domain.dto;

import com.present.GifticBe.domain.Posts;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostsResponseDto {

    private Long id;

    private String title;

    private String content;

    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

    public static List<PostsResponseDto> from(List<Posts> allPosts) {
        List<PostsResponseDto> allPostsResponseDto = new ArrayList<>();
        for(Posts posts : allPosts) {
            allPostsResponseDto.add(new PostsResponseDto(posts));
        }
        return allPostsResponseDto;
    }
}
