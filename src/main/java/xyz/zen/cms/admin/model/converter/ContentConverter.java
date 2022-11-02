package xyz.zen.cms.admin.model.converter;

import org.springframework.stereotype.Service;
import xyz.zen.cms.admin.model.dto.ArticleContentDto;
import xyz.zen.cms.admin.repository.entity.ArticleEntity;

@Service
public class ContentConverter implements Converter<ArticleEntity, ArticleContentDto> {

    @Override
    public ArticleContentDto convert(ArticleEntity articleEntity) {
        return new ArticleContentDto(articleEntity.getId(), articleEntity.getTitle(),
                articleEntity.getAuthor(),
                articleEntity.getContent(),
                articleEntity.getPath()
        );
    }
}
