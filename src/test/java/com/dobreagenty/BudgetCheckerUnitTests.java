package com.dobreagenty;

import com.dobreagenty.behaviours.BudgetCheckerBehaviour;
import com.dobreagenty.behaviours.CostEvaluatorBehaviour;
import com.dobreagenty.misc.District;
import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;
import com.dobreagenty.misc.OfferTypeEnum;
import com.dobreagenty.payloads.BudgetEvaluation;
import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.lang.acl.ACLMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BudgetCheckerUnitTests {
    @Test
    @DisplayName("Cost evaluator correct data")
    void costEvaluatorCorrectData() {
        BudgetCheckerBehaviour behaviour = new BudgetCheckerBehaviour();
        Offer offer = new Offer();
        offer.id = UUID.randomUUID();
        offer.district = new District(DistrictEnum.Mokotow);
        offer.type = new OfferType(OfferTypeEnum.Library);
        offer.name = "Test Name";
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent(offer.toString());
        BudgetEvaluation evaluation = behaviour.evaluate(msg);
        double expected = offer.type.price <= offer.district.budget ? 1 : 0;
        assertEquals(expected, evaluation.result);
    }
}