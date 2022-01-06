package com.dobreagenty.payloads;

import org.json.JSONObject;

public class BudgetEvaluation extends BaseEvaluation {
    public int result;

    public BudgetEvaluation(Offer offer) {
        super(offer);
    }

    public BudgetEvaluation(JSONObject json) {
        super(json);
        result = json.getInt("result");
    }

    @Override
    public void evaluate() {
        result = offer.type.price <= offer.district.budget ? 1 : 0;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", offer.id.toString());
        json.put("name", offer.name);
        json.put("type", offer.type.type.ordinal());
        json.put("district", offer.district.districtEnum.ordinal());
        json.put("result", result);
        return json.toString();
    }
}