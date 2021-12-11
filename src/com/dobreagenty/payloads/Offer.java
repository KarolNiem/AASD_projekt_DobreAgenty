package com.dobreagenty.payloads;


import org.json.JSONObject;

public class Offer {
    public OfferType type;

    public Offer(JSONObject json) {
        type = OfferType.values()[json.getInt("type")];
    }
}
