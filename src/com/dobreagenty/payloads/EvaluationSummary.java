package com.dobreagenty.payloads;

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

    public boolean IsCompleted() {
        return costEvaluation != null && ageStructEvaluation != null &&
                usabilityEvaluation != null && budgetEvaluation != null;
    }
}
