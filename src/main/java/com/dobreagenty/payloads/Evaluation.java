package com.dobreagenty.payloads;

import org.json.JSONObject;

public class Evaluation {
    public Offer offer;
    public double result;
    public double costResult;
    public double ageStructResult;
    public double usabilityResult;
    public double budgetResult;

    public Evaluation(EvaluationSummary summary) {
        offer = summary.offer;
        result = evaluate(summary);
        costResult=summary.costEvaluation;
        ageStructResult=summary.ageStructEvaluation;
        usabilityResult=summary.usabilityEvaluation;
        budgetResult=summary.budgetEvaluation;
    }

    public Evaluation(JSONObject json) {
        offer = new Offer(json);
        result = json.getDouble("result");
        costResult = json.getDouble("costResult");
        ageStructResult=json.getDouble("ageStructResult");
        usabilityResult=json.getDouble("usabilityResult");
        budgetResult=json.getInt("budgetEvaluation");
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
        json.put("costResult",costResult);
        json.put("ageStructResult",ageStructResult);
        json.put("usabilityResult",usabilityResult);
        json.put("budgetResult",budgetResult);
        return json.toString();
    }
}
