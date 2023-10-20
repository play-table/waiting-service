package com.example.waiting.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    public final static String STORE = "store"; //store의 정보를 넘겨받은 listener(consumer)


    @Bean
    public NewTopic store() {
        return new NewTopic(STORE, 1, (short) 1);
    }


}
