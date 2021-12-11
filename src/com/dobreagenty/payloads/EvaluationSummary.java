package com.dobreagenty.payloads;

import org.json.JSONObject;
import java.util.UUID;

public class EvaluationSummary {
    public UUID offerID;
    public CostEvaluation costEvaluation;
    public AgeStructEvaluation ageStructEvaluation;
    public UsabilityEvaluation usabilityEvaluation;
    public BudgetEvaluation budgetEvaluation;

    public EvaluationSummary(Offer offer) {
        this.offerID = offer.id;
    }

    public EvaluationSummary(JSONObject json) {
        // CHANGE
    }

    public boolean isCompleted() {
        return costEvaluation != null && ageStructEvaluation != null &&
                usabilityEvaluation != null && budgetEvaluation != null;
    }

    @Override
    public String toString() {
        return ""; // CHANGE
    }
}
