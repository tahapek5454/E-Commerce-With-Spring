package com.ecommerce.secondhand.user.repository;

import com.ecommerce.secondhand.user.model.entity.Advertisement;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementElasticSearchRepository extends
        ElasticsearchRepository<Advertisement, String> {

    List<Advertisement> findByTitleLike(String title);

}
