package xyz.zen.cms.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.zen.cms.admin.exception.NotFoundException;
import xyz.zen.cms.admin.model.converter.Converter;
import xyz.zen.cms.admin.model.dto.ArticleContentDto;
import xyz.zen.cms.admin.model.dto.ArticleInfoDto;
import xyz.zen.cms.admin.model.dto.ArticleUpdateDto;
import xyz.zen.cms.admin.repository.ArticleRepository;
import xyz.zen.cms.admin.repository.entity.ArticleEntity;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final Converter<ArticleEntity, ArticleInfoDto> articleConverter;

    private final Converter<ArticleEntity, ArticleContentDto> contentConverter;

    public ArticleServiceImpl(ArticleRepository articleRepository, Converter<ArticleEntity, ArticleInfoDto> articleConverter,
                              Converter<ArticleEntity, ArticleContentDto> contentConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
        this.contentConverter = contentConverter;
    }

    @Override
    @Transactional
    public void create(ArticleContentDto articleDto) {
        ArticleEntity build = ArticleEntity.builder()
                .id(articleDto.id())
                .published(false)
                .title(articleDto.title())
                .author(articleDto.author())
                .content(articleDto.content())
                .path(articleDto.path())
                .build();
        articleRepository.save(build);
    }

    @Override
    @Transactional
    public void update(ArticleContentDto articleDto) {
        ArticleEntity articleEntity = articleRepository.findById(articleDto.id()).orElseThrow();
        articleEntity.setContent(articleDto.content());
        articleEntity.setPath(articleDto.path());
        articleEntity.setTitle(articleDto.title());
        articleRepository.save(articleEntity);
    }

    @Override
    public List<ArticleInfoDto> getAll() {
        return articleRepository.findAll().stream()
                .map(articleConverter::convert)
                .toList();
    }

    @Override
    public List<ArticleInfoDto> getPublished() {
        return articleRepository.findAllByPublished(true)
                .stream().map(articleConverter::convert)
                .toList();
    }

    @Override
    public long getCount() {
        return articleRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleContentDto> getContent(String path) {
        return articleRepository.findByPath(path).map(contentConverter::convert);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleContentDto> getContent(Long id) {
        return articleRepository.findById(id).map(contentConverter::convert);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void partUpdate(final ArticleUpdateDto dto) {
        ArticleEntity articleEntity = articleRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Article by id: " + dto.getId()));
        articleEntity.setPublished(dto.getPublished());
        articleRepository.save(articleEntity);
    }
}
