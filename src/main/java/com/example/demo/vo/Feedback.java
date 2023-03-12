package com.example.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Feedback implements Serializable {

    private Long lifeCycleId;
    private String name;
    private String gcId;
    private Gender gender;
    private String id;
    private ParkingLotType parkingLotType;
    private boolean ePay;
    private Long createTime;

    public enum Gender {
        MALE(0),
        FEMALE( 1);
        private int index;
        private Gender( int index) {
            this.index = index;
        }
        private int getIndex(Gender gender){
            return  gender.index;
        }
    }

    public enum ParkingLotType {
        UNDERGROUND(0),
        OUTSIDE( 1);
        private int index;
        private ParkingLotType( int index) {
            this.index = index;
        }
        private int getIndex(ParkingLotType parkingLotType){
            return  parkingLotType.index;
        }
    }
}
