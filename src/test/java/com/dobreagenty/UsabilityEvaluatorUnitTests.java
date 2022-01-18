package com.dobreagenty;

import com.dobreagenty.behaviours.CostEvaluatorBehaviour;
import com.dobreagenty.behaviours.UsabilityEvaluatorBehaviour;
import com.dobreagenty.misc.District;
import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;
import com.dobreagenty.misc.OfferTypeEnum;
import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Offer;
import com.dobreagenty.payloads.UsabilityEvaluation;
import jade.lang.acl.ACLMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsabilityEvaluatorUnitTests {
    @Test
    @DisplayName("Usability evaluation test")
    void usabilityEvaluatorTest() {
        UsabilityEvaluatorBehaviour behaviour = new UsabilityEvaluatorBehaviour();
        Offer offer = new Offer();
        offer.id = UUID.randomUUID();
        offer.district = new District(DistrictEnum.Mokotow);
        offer.type = new OfferType(OfferTypeEnum.Library);
        offer.name = "Test Name";
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent(offer.toString());
        UsabilityEvaluation evaluation = behaviour.evaluate(msg);

        double averageCoeff = evaluation.getAveragePlacesPerPerson();
        double pop = offer.getNumberOfTargetPopulation();
        double places = offer.district.getNumberOfPlaces(offer.district.districtEnum, offer.type.type);
        if (pop == 0) pop = 0.00001;
        double coeff = places / pop;
        double expected = Math.min(1, Math.max(0, 1 - 0.5 * coeff / averageCoeff));
        assertEquals(expected, evaluation.result);
    }
}