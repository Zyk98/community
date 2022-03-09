package com.diy.community.dao.elasticsearch;

import com.diy.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName DiscussPostRepository.java
 * @Description DiscussPost Repository
 */

@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost,Integer> {
}
