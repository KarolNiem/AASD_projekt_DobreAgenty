package com.dobreagenty;

import com.dobreagenty.behaviours.AgeStructEvaluatorBehaviour;
import com.dobreagenty.behaviours.GlobalEvaluatorBehaviour;
import com.dobreagenty.misc.District;
import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;
import com.dobreagenty.misc.OfferTypeEnum;
import com.dobreagenty.payloads.AgeStructEvaluation;
import com.dobreagenty.payloads.Evaluation;
import com.dobreagenty.payloads.EvaluationSummary;
import com.dobreagenty.payloads.Offer;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalEvaluatorUnitTests {
    @Test
    @DisplayName("Global evaluation within budget")
    void globalEvaluatorTestWithinBudget() {
        EvaluationSummary summary = new EvaluationSummary();
        Offer offer = new Offer();
        offer.type = new OfferType(OfferTypeEnum.BusStop);
        offer.district = new District(DistrictEnum.Srodmiescie);
        summary.offer = offer;

        summary.budgetEvaluation = 0;
        summary.ageStructEvaluation = summary.costEvaluation = summary.usabilityEvaluation = 1.0;

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(summary.toString());

        GlobalEvaluatorBehaviour behaviour = new GlobalEvaluatorBehaviour();
        Evaluation evaluation = behaviour.evaluate(msg);
        assertEquals(0.0, evaluation.result);
    }

    @Test
    @DisplayName("Global evaluation over budget")
    void globalEvaluatorTestOverBudget() {
        EvaluationSummary summary = new EvaluationSummary();
        Offer offer = new Offer();
        offer.type = new OfferType(OfferTypeEnum.BusStop);
        offer.district = new District(DistrictEnum.Srodmiescie);
        summary.offer = offer;
        summary.budgetEvaluation = 1;
        summary.ageStructEvaluation = 0.4;
        summary.costEvaluation = 0.5;
        summary.usabilityEvaluation = 0.6;

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(summary.toString());

        GlobalEvaluatorBehaviour behaviour = new GlobalEvaluatorBehaviour();
        Evaluation evaluation = behaviour.evaluate(msg);
        assertEquals(0.5, evaluation.result);
    }
}