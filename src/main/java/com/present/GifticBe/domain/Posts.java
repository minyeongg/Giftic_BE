package com.present.GifticBe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private Integer writer_id;

    private Integer image_id;

    private Integer likes_id;

    private Integer users_id;

    private Integer category_id;
}
