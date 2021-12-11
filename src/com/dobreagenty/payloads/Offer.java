package com.dobreagenty.payloads;

import org.json.JSONObject;
import java.util.UUID;

public class Offer {
    public UUID id = UUID.randomUUID();
    public OfferType type;

    public Offer(JSONObject json) {
        id = UUID.fromString(json.getString("id"));
        type = OfferType.values()[json.getInt("type")];
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", id.toString());
        json.put("type", type.ordinal());
        return json.toString();
    }
}
