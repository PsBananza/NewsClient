package com.dmitry.NewsClient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dmitry.NewsClient.dto.BaseSuccessResponse;
import com.dmitry.NewsClient.dto.CreateNewsSuccessResponse;
import com.dmitry.NewsClient.dto.CustomSuccessResponse;
import com.dmitry.NewsClient.dto.GetNewsOutDto;
import com.dmitry.NewsClient.dto.NewsDto;
import com.dmitry.NewsClient.dto.PageableResponse;
import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.Tag;
import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.mapstruct.GetNewsOutDtoMapper;
import com.dmitry.NewsClient.repository.NewsRep;
import com.dmitry.NewsClient.repository.RepositoryUser;
import com.dmitry.NewsClient.repository.TagRep;
import com.dmitry.NewsClient.service.newsInterface.NewsService;
import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImp implements NewsService {
    private final TagRep tagRepo;
    private final EntityManager entityManager;
    private final GetNewsOutDtoMapper newsMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public CreateNewsSuccessResponse createNews(NewsDto newsDto) {
        NewsEntity newsEntity = new NewsEntity();
//        UserEntity entity = userRepo.findById(UUID.fromString(newsDto.getUserId()));
//        UserEntity entity = mongoRep.mongoByUserId(newsDto.getUserId());
        Query query = new Query(Criteria.where("_id").is(newsDto.getUserId()));
        UserEntity entity = mongoTemplate.findById(new ObjectId(newsDto.getUserId()), UserEntity.class);
        List<Tag> list = new ArrayList<>();

        for (String tags: newsDto.getTags()) {
            Tag tag = new Tag();
            tag.setTitle(tags);
            list.add(tag);
        }
        newsEntity.setDescription(newsDto.getDescription())
                .setImage(FileServiceImp.avatar)
                .setTitle(newsDto.getTitle())
                .setTags(list)
                .setUser(entity)
                .setUsername(entity.getName());
        mongoTemplate.save(newsEntity, "news");
//        for (Tag tags: list) {
//            tagRepo.save(tags);
//        }
        CreateNewsSuccessResponse response = new CreateNewsSuccessResponse();
        response.setId(newsEntity.getId());
        return response;
    }

    @Override
    public CustomSuccessResponse getNews(int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
//        Page<NewsEntity> pagedResult = newsRepo.findAll(pageable);
        Query query = new Query();
        query.with(pageable);
        List<NewsEntity> list1 = mongoTemplate.find(query, NewsEntity.class, "news");
        Page<NewsEntity> pagedResult = new PageImpl<>(list1);
        List<GetNewsOutDto> getNewsOutDto = new ArrayList<>();
        PageableResponse<GetNewsOutDto> response = new PageableResponse<>();
        for (NewsEntity entity : pagedResult) {
            GetNewsOutDto newsEntity = newsMapper.newsEntityOut(entity);
            getNewsOutDto.add(newsEntity);
        }
        response.setContent(getNewsOutDto);
        response.setNumberOfElements(pagedResult.getTotalElements());
        return new CustomSuccessResponse(response);
    }

    @Override
    public PageableResponse findNews(int page, int perPage, String author, String keywords, String tags) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> criteriaQuery = criteriaBuilder.createQuery(NewsEntity.class);
        Root<NewsEntity> newsRoot = criteriaQuery.from(NewsEntity.class);

        List<Predicate> predicateList = new ArrayList<>();
        List<Predicate> tagsPredicateList = new ArrayList<>();

        if (author != null) {
            predicateList.add(criteriaBuilder.equal(newsRoot.get("username"), author));
        }
        if (keywords != null) {
            predicateList.add(criteriaBuilder.like(newsRoot.get("title"), keywords));
        }
        if (tags != null) {
            for (String tag : tags.split(",")) {
                Tag tagEntity = tagRepo.findByTitle(tag);
                tagsPredicateList.add(criteriaBuilder.isMember(tagEntity, newsRoot.get("tags")));
            }
            Predicate[] tagsPredicate = new Predicate[tagsPredicateList.size()];
            tagsPredicateList.toArray(tagsPredicate);
            predicateList.add(criteriaBuilder.or(tagsPredicate));
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        criteriaQuery.select(newsRoot).where(criteriaBuilder.and(predicates));

        TypedQuery<NewsEntity> query = entityManager.createQuery(criteriaQuery);

        query.setFirstResult((page - 1) * perPage);
        query.setMaxResults(perPage);

        List<NewsEntity> newsEntityList = query.getResultList();
        List<GetNewsOutDto> getNewsOutDtos = new ArrayList<>();

        for (NewsEntity entity : newsEntityList) {
            GetNewsOutDto newsEntity = newsMapper.newsEntityOut(entity);
            getNewsOutDtos.add(newsEntity);
        }
        PageableResponse pageableResponse = new PageableResponse();
        pageableResponse.setContent(getNewsOutDtos)
                .setNumberOfElements(Long.valueOf(getNewsOutDtos.size()));

        return pageableResponse;
    }


    @Override
    public BaseSuccessResponse deleteNews(String id) {
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        NewsEntity newsEntity;
        newsEntity = mongoTemplate.findAndRemove(query, NewsEntity.class, "news");
        return new BaseSuccessResponse();
    }

    @Override
    public BaseSuccessResponse putNews(String id, NewsDto newsDto) {

        NewsEntity entity = new NewsEntity();
        entity.setDescription(newsDto.getDescription())
                .setTitle(newsDto.getTitle())
                .setImage(FileServiceImp.avatar);
//        List<Tag> list = new ArrayList<>();
//        for (String tags: newsDto.getTags()) {
//            Tag tag = new Tag();
//            tag.setTitle(tags);
//            list.add(tag);
//        }
//        entity.setTags(list);
//        newsRepo.save(entity);

        List<Tag> list = new ArrayList<>();

        for (String tags: newsDto.getTags()) {
            Tag tag = new Tag();
            tag.setTitle(tags);
            list.add(tag);
        }

        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        Update update = new Update().set("description", newsDto.getDescription())
                .set("image", newsDto.getImage())
                .set("title", newsDto.getTitle())
                .addToSet("tags", list);
        NewsEntity newsEntity = mongoTemplate.findAndModify(query, update, NewsEntity.class, "news");

        return new BaseSuccessResponse();
    }
}
