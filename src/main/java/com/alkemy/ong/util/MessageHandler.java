package com.alkemy.ong.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class MessageHandler {

    public final String userFound;
    public final String resourceNotFound;
    public final String categoryNotFound;
    public final String activityNotFound;
    public final String newsNotFound;
    public final String categoryIdRequired;
    @Autowired
    public MessageHandler(@Value("${user.found}") String userFound,
    		@Value("${news.notFound}")String newsNotFound,
    		@Value("${category-id.required}") String categoryIdRequired,
    		@Value("${category.notFound}") String categoryNotFound,
            @Value("${category.notFound}") String activityNotFound,
    		@Value ("${resource.notFound}") String resourceNotFound) {

        this.userFound = userFound;
        this.resourceNotFound= resourceNotFound;
        this.categoryNotFound= categoryNotFound;
        this.activityNotFound = activityNotFound;
        this.newsNotFound = newsNotFound;
        this.categoryIdRequired = categoryIdRequired;
    }

}
