package com.dobreagenty.payloads;


import org.json.JSONObject;

public class Offer {
    public OfferType type;

    public Offer(JSONObject json) {
        type = OfferType.values()[json.getInt("type")];
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("type", type.ordinal());
        return json.toString();
    }
}
