package com.example.demo.config;

import com.al.cc.java.p3.FeedBackSchema;
import org.springframework.cglib.core.Converter;

public class FeedbackEntityConverter implements Converter {
    @Override
    public Object convert(Object source, Class type, Object target) {
        if(source instanceof FeedBackSchema.Gender){
                return ((FeedBackSchema.Gender) source).getNumber();
        }
        if(source instanceof FeedBackSchema.ParkingLotType){
            return ((FeedBackSchema.ParkingLotType) source).getNumber();
        }
        if(source instanceof com.al.cc.java.p2.FeedBackSchema.Gender){
            return ((com.al.cc.java.p2.FeedBackSchema.Gender) source).getNumber();
        }
        if(source instanceof com.al.cc.java.p2.FeedBackSchema.ParkingLotType){
            return ((com.al.cc.java.p2.FeedBackSchema.ParkingLotType) source).getNumber();
        }
        if(source instanceof Integer){
            return source;
        }
        if(source instanceof Long){
            return source;
        }
        if(source instanceof Boolean){
            return (boolean)source?1:0;
        }
        if(source instanceof String){
            return source;
        }
        return null;
    }
}
