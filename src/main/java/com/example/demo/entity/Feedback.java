package com.example.demo.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "Feedback")
public class Feedback {
    @DynamoDBHashKey(attributeName="key")
    @DynamoDBAutoGeneratedKey
    private String key;
    private Long lifeCycleId;
    private String name;
    private String gcId;
    private Integer gender;
    private String id;
    private Integer parkingLotType;
    private Integer ePay;

}
