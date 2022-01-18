package com.dobreagenty;

import com.dobreagenty.agents.CostEvaluatorAgent;
import com.dobreagenty.behaviours.CostEvaluatorBehaviour;
import com.dobreagenty.behaviours.CustomerBehaviour;
import com.dobreagenty.misc.District;
import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;
import com.dobreagenty.misc.OfferTypeEnum;
import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.lang.acl.ACLMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CostEvaluatorUnitTests {
    @Test
    @DisplayName("Cost evaluation test")
    void costEvaluatorTest() {
        CostEvaluatorBehaviour behaviour = new CostEvaluatorBehaviour();
        Offer offer = new Offer();
        offer.id = UUID.randomUUID();
        offer.district = new District(DistrictEnum.Mokotow);
        offer.type = new OfferType(OfferTypeEnum.Library);
        offer.name = "Test Name";
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent(offer.toString());
        CostEvaluation evaluation = behaviour.evaluate(msg);
        double expected = 1.0 - Math.min((double)offer.type.price / (double)offer.district.budget, 1.0);
        assertEquals(expected, evaluation.result);
    }
}