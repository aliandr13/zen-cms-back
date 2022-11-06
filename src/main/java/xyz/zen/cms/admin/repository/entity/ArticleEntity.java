package xyz.zen.cms.admin.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

@Builder
@Getter
@Setter
@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", length = 4056)
    private String content;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "path", unique = true, nullable = false)
    private String path;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "published", nullable = false)
    private boolean published = false;


    @Version
    @Column(name = "version")
    @Setter(AccessLevel.NONE)
    private Integer version;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date", insertable = false)
    private Date modifyDate;

}