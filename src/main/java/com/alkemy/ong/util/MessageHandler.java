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
    public final String welcomeEmail;
    public final String errorEmail;
    public final String contactEmail;


    @Autowired
    public MessageHandler(@Value("${user.found}") String userFound,
    		@Value("${news.notFound}")String newsNotFound,
    		@Value("${category-id.required}") String categoryIdRequired,
    		@Value("${category.notFound}") String categoryNotFound,
    		@Value ("${resource.notFound}") String resourceNotFound,
                          @Value("${welcome.emailText}") String welcomeEmail,
                          @Value("${error.emailText}") String errorEmail,
                          @Value("${contact.emailText}") String contactEmail) {

        this.userFound = userFound;
        this.resourceNotFound= resourceNotFound;
        this.categoryNotFound= categoryNotFound;
        this.newsNotFound = newsNotFound;
        this.categoryIdRequired = categoryIdRequired;
        this.welcomeEmail = welcomeEmail;
        this.errorEmail = errorEmail;
        this.contactEmail = contactEmail;
    }

}
