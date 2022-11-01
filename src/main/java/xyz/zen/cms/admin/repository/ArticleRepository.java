package xyz.zen.cms.admin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.zen.cms.admin.repository.entity.ArticleEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAll();

    Optional<ArticleEntity> findByPath(String path);

}