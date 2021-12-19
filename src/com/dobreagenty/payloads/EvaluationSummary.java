package com.dobreagenty.payloads;

import com.dobreagenty.misc.OfferType;
import com.dobreagenty.misc.OfferTypeEnum;
import org.json.JSONObject;
import java.util.UUID;

public class EvaluationSummary {
    public Offer offer;
    public double costEvaluation = -1.0;
    public double ageStructEvaluation = -1.0;
    public double usabilityEvaluation = -1.0;
    public int budgetEvaluation = -1;

    public EvaluationSummary(Offer offer) {
        this.offer = offer;
    }

    public EvaluationSummary(JSONObject json) {
        offer = new Offer(json);
        costEvaluation = json.getDouble("costEvaluation");
        ageStructEvaluation = json.getDouble("ageStructEvaluation");
        usabilityEvaluation = json.getDouble("usabilityEvaluation");
        budgetEvaluation = json.getInt("budgetEvaluation");
    }

    public boolean isCompleted() {
        return costEvaluation > 0 && ageStructEvaluation > 0 &&
                usabilityEvaluation > 0 && budgetEvaluation > 0;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", offer.id.toString());
        json.put("name", offer.name);
        json.put("type", offer.type.type.ordinal());
        json.put("district", offer.district.districtEnum.ordinal());
        json.put("costEvaluation", costEvaluation);
        json.put("ageStructEvaluation", ageStructEvaluation);
        json.put("usabilityEvaluation", usabilityEvaluation);
        json.put("budgetEvaluation", budgetEvaluation);
        return json.toString();
    }
}
