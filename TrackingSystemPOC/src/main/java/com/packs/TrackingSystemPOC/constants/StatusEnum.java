package com.packs.TrackingSystemPOC.constants;

public enum StatusEnum {
    AC("Added to Wish Cart"),
    AW("Added to Wish List"),
    AA("Address Added"),
    OS("Order Summary"),
    PP("Payment Processed"),
    OP("Order Placed");

    private String code;

    StatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static StatusEnum fromCode(String code) {
        for (StatusEnum status :StatusEnum.values()){
            if (status.getCode().equals(code)){
                return status;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!");
    }
}