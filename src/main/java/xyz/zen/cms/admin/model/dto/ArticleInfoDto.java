package xyz.zen.cms.admin.model.dto;

import java.time.LocalDate;

public record ArticleInfoDto(Long id, String title, boolean published, String author, String path, LocalDate created, LocalDate updated) {
}

