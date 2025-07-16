package com.bestpratice.transation.config;

import com.mongodb.client.MongoDatabase;
import org.bson.json.Converter;
import org.bson.json.StrictJsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class MongoDateConfig {
    @Autowired
    private MongoDatabaseFactory mongoDatabaseFactory;

//    @Bean
//    public MongoCustomConversions mongoCustomConversions() {
//        return new MongoCustomConversions(Arrays.asList(
//                new LocalDateTimeToStringConverter(),
//                new StringToLocalDateTimeConverter()
//        ));
//    }
//
//    @WritingConverter
//    static class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {
//
//        @Override
//        public void convert(LocalDateTime localDateTime, StrictJsonWriter strictJsonWriter) {
//
//        }
//    }

}
