package com.example.demo.entity;

import lombok.Data;

@Data
public class Feedback {
    private Long lifeCycleId;
    private String name;
    private String gcId;
    private Integer gender;
    private String id;
    private Integer parkingLotType;
    private Integer ePay;

}
