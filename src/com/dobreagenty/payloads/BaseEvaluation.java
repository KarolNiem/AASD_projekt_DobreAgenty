package com.dobreagenty.payloads;

import org.json.JSONObject;
import java.util.UUID;

public abstract class BaseEvaluation {
    public UUID offerID;

    public BaseEvaluation(Offer offer) {
        offerID = offer.id;
    }

    public BaseEvaluation(JSONObject json) {
        offerID = UUID.fromString(json.getString("offerID"));
    }
}
