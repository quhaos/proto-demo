package com.example.demo.controller;

import com.al.cc.java.p2.FeedBackSchema;
import com.example.demo.config.FeedbackEntityConverter;
import com.example.demo.entity.Feedback;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class HelloWorldController {
    private FeedbackEntityConverter converter = new FeedbackEntityConverter();
    private Map<String,BeanCopier> cache = new ConcurrentHashMap<>();
    @RequestMapping(value="/feedbackJson" ,
            method=RequestMethod.POST ,
            consumes="application/json;charset=UTF-8")
    public String addFeedback(@RequestBody com.example.demo.vo.Feedback feedback){
        System.out.println(feedback);
        return feedback.toString();
    }
    @RequestMapping(value="/feedbackPro2" ,
            method=RequestMethod.POST ,
            consumes="application/x-protobuf;charset=UTF-8")
    public ResponseEntity addFeedback(@RequestBody FeedBackSchema.Feedback feedback){
        System.out.println("============= p2 interface ==================");
        System.out.printf("lifeCycleId:%d \n",feedback.getLifeCycleId());
        Feedback feedbackEntity = new Feedback();
        String key = generateKey(FeedBackSchema.Feedback.class, Feedback.class);
        BeanCopier beanCopier;
        if(cache.containsKey(key)){
            beanCopier = cache.get(key);
        }else{
            beanCopier = BeanCopier.create(FeedBackSchema.Feedback.class, Feedback.class, true);
            cache.put(key,beanCopier);
        }
        beanCopier.copy(feedback,feedbackEntity,converter);
        System.out.println(feedbackEntity);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value="/feedbackPro3" ,
            method=RequestMethod.POST ,
            consumes="application/x-protobuf;charset=UTF-8")
    public ResponseEntity addFeedback(@RequestBody com.al.cc.java.p3.FeedBackSchema.Feedback feedback){
        System.out.println("============= p3 interface ==================");
        System.out.printf("lifeCycleId:%d \n",feedback.getLifeCycleId());
        BeanCopier beanCopier = BeanCopier.create(com.al.cc.java.p3.FeedBackSchema.Feedback.class, Feedback.class, true);
        Feedback feedbackEntity = new Feedback();
        beanCopier.copy(feedback,feedbackEntity,converter);
        System.out.println(feedbackEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    private static String generateKey(Class<?> source, Class<?> target) {
        return source.getName() + target.getName();
    }


}
