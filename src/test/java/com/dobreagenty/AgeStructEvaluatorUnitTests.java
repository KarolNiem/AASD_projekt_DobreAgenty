package com.dobreagenty;

import com.dobreagenty.behaviours.AgeStructEvaluatorBehaviour;
import com.dobreagenty.behaviours.CostEvaluatorBehaviour;
import com.dobreagenty.misc.District;
import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;
import com.dobreagenty.misc.OfferTypeEnum;
import com.dobreagenty.payloads.AgeStructEvaluation;
import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.lang.acl.ACLMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgeStructEvaluatorUnitTests {
    @Test
    @DisplayName("Age struct evaluation test")
    void ageStructEvaluatorTest() {
        AgeStructEvaluatorBehaviour behaviour = new AgeStructEvaluatorBehaviour();
        Offer offer = new Offer();
        offer.id = UUID.randomUUID();
        offer.district = new District(DistrictEnum.Mokotow);
        offer.type = new OfferType(OfferTypeEnum.Library);
        offer.name = "Test Name";
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent(offer.toString());
        AgeStructEvaluation evaluation = behaviour.evaluate(msg);
        double expected = Math.min(1, 2 * (double) offer.getNumberOfTargetPopulation()
                / (double) offer.district.getTotalPopulation(offer.district.districtEnum));
        assertEquals(expected, evaluation.result);
    }
}