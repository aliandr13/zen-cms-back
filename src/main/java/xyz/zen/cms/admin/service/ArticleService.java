package xyz.zen.cms.admin.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.zen.cms.admin.model.dto.ArticleContentDto;
import xyz.zen.cms.admin.model.dto.ArticleInfoDto;
import xyz.zen.cms.admin.model.dto.ArticleUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    @Transactional
    void create(ArticleContentDto articleDto);

    @Transactional
    void update(ArticleContentDto articleDto);

    List<ArticleInfoDto> getAll();

    List<ArticleInfoDto> getPublished();

    long getCount();

    Optional<ArticleContentDto> getContent(String path);

    Optional<ArticleContentDto> getContent(Long id);

    void delete(Long id);

    void partUpdate(ArticleUpdateDto dto);
}
