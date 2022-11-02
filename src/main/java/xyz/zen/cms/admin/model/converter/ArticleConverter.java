package xyz.zen.cms.admin.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zen.cms.admin.model.dto.ArticleInfoDto;
import xyz.zen.cms.admin.repository.entity.ArticleEntity;

import java.time.LocalDate;
import java.util.Date;

@Service
public class ArticleConverter implements Converter<ArticleEntity, ArticleInfoDto> {

    private final Converter<Date, LocalDate> dateConverter;

    @Autowired
    public ArticleConverter(Converter<Date, LocalDate> dateConverter) {
        this.dateConverter = dateConverter;
    }

    @Override
    public ArticleInfoDto convert(ArticleEntity articleEntity) {
        return new ArticleInfoDto(articleEntity.getId(),
                articleEntity.getTitle(),
                articleEntity.isPublished(),
                articleEntity.getAuthor(),
                articleEntity.getPath(),
                dateConverter.convert(articleEntity.getCreateDate()),
                dateConverter.convert(articleEntity.getModifyDate())
        );
    }
}
