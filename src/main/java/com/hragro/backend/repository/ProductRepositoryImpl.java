package com.hragro.backend.repository;

import com.hragro.backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary  // <-- Add this to make it the primary bean
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Product> searchProducts(String search, String category, Pageable pageable) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if (search != null && !search.isEmpty()) {
            Criteria searchCriteria = new Criteria().orOperator(
                Criteria.where("name").regex(search, "i"),
                Criteria.where("desc").regex(search, "i")
            );
            criteriaList.add(searchCriteria);
        }

        if (category != null && !category.isEmpty()) {
            criteriaList.add(Criteria.where("category").is(category));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        long total = mongoTemplate.count(query, Product.class);
        query.with(pageable);
        List<Product> products = mongoTemplate.find(query, Product.class);

        return new PageImpl<>(products, pageable, total);
    }
}