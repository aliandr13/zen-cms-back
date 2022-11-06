package xyz.zen.cms.admin.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArticleUpdateDto {
    Long id;
    Boolean published;
    String title;
    String path;
    String author;
    LocalDate created;
    LocalDate updated;
}

