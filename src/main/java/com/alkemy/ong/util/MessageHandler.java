package com.alkemy.ong.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class MessageHandler {

    public final String userFound;
    public final String newsNotFound;
    public final String categoryNotFound;

    @Autowired
    public MessageHandler(@Value("${user.found}") String userFound, @Value("${news.notFound}") String newsNotFound,
                          @Value("${category.notFound}") String categoryNotFound) {
        this.userFound = userFound;
        this.newsNotFound = newsNotFound;
        this.categoryNotFound = categoryNotFound;
    }

}
