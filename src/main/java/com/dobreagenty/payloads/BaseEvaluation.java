package com.dobreagenty.payloads;

import org.json.JSONObject;
import java.util.UUID;

public abstract class BaseEvaluation {
    public Offer offer;

    public BaseEvaluation(Offer offer) {
        this.offer = offer;
        evaluate();
    }

    public BaseEvaluation(JSONObject json) {
        offer = new Offer(json);
        evaluate();
    }

    public abstract void evaluate();
}
