package com.spring.springsenior.ElasticSearch.repository;

import com.spring.springsenior.ElasticSearch.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/4
 * @description :
 */
@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article,Integer> {
}
