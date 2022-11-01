package xyz.zen.cms.admin.service;

import org.springframework.stereotype.Service;
import xyz.zen.cms.admin.model.converter.Converter;
import xyz.zen.cms.admin.model.dto.ArticleContentDto;
import xyz.zen.cms.admin.model.dto.ArticleInfoDto;
import xyz.zen.cms.admin.repository.ArticleRepository;
import xyz.zen.cms.admin.repository.entity.ArticleEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final Converter<ArticleEntity, ArticleInfoDto> articleConverter;

    private final Converter<ArticleEntity, ArticleContentDto> contentConverter;

    public ArticleService(ArticleRepository articleRepository, Converter<ArticleEntity, ArticleInfoDto> articleConverter,
                          Converter<ArticleEntity, ArticleContentDto> contentConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
        this.contentConverter = contentConverter;
    }

    public void create(ArticleContentDto articleDto) {
        articleRepository.save(ArticleEntity.builder()
                .published(false)
                .title(articleDto.title())
                .author(articleDto.author())
                .content(articleDto.content())
                .path(articleDto.path())
                .build());
    }

    public List<ArticleInfoDto> getAll() {
        return articleRepository.findAll().stream().map(articleConverter::convert).collect(Collectors.toList());
    }

    public long getCount() {
        return articleRepository.count();
    }

    public Optional<ArticleContentDto> getContent(String path) {
        return articleRepository.findByPath(path).map(contentConverter::convert);
    }
}
