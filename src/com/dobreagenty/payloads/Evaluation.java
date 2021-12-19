package com.dobreagenty.payloads;

import org.json.JSONObject;

public class Evaluation {
    public Offer offer;
    public double result;

    public Evaluation(EvaluationSummary summary) {
        offer = summary.offer;
        result = evaluate(summary);
    }

    public Evaluation(JSONObject json) {
        offer = new Offer(json);
        result = json.getDouble("result");
    }

    public double evaluate(EvaluationSummary summary) {
        if (summary.budgetEvaluation == 1) {
            return (summary.costEvaluation + summary.usabilityEvaluation + summary.ageStructEvaluation) / 3.0;
        } else {
            return 0.0;
        }
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
