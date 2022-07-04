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
    public final String newsNotFound;
    public final String categoryIdRequired;
    public final String slidesNotFound;

    @Autowired
    public MessageHandler(@Value("${user.found}") String userFound,
    		@Value("${news.notFound}")String newsNotFound,
    		@Value("${category-id.required}") String categoryIdRequired,
    		@Value("${category.notFound}") String categoryNotFound,
    		@Value ("${resource.notFound}") String resourceNotFound,
    		@Value("${slides.not-found}") String slidesNotFound) {

        this.userFound = userFound;
        this.resourceNotFound= resourceNotFound;
        this.categoryNotFound= categoryNotFound;
        this.newsNotFound = newsNotFound;
        this.categoryIdRequired = categoryIdRequired;
        this.slidesNotFound = slidesNotFound;
    }

}
