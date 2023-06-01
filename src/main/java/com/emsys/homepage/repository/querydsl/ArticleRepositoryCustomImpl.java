package com.emsys.homepage.repository.querydsl;

import com.emsys.homepage.domain.Article;
import com.emsys.homepage.domain.QArticle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    /**
     * 모든 고유한 해시태그를 조회합니다.
     * @return 고유한 해시태그 리스트
     */
    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

        return from(article)
                .distinct()
                .select(article.hashtag)    // 하나의 컬럼만 내보냄
                .where(article.hashtag.isNotNull())
                .fetch();
    }
}