package com.packs.TrackingSystemPOC.constants;

public enum StatusEnum
{
    AW("Added to Wish List"),
    AC("Added to Wish Cart"),
    AA("Address Added"),
    OS("Order Summary"),
    PP("Payment Processed"),
    OP("Order Placed");

    private String status;

    StatusEnum(String selectedstatus) {
        this.status = selectedstatus;
    }

    public String getStatus() {
        return status;
    }
}