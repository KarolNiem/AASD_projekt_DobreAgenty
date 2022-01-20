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
import com.dobreagenty.behaviours.CustomerSystemInterfaceBehaviour;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerInterfaceUnitTest {

    @Test
    @DisplayName("CustomerInterface offer generation")
    void customerInterfaceTestOffer() {
        JSONObject customerData = new JSONObject();
        customerData.put("phoneNumber", "123456789");
        customerData.put("surname", "Doe");
        customerData.put("name", "John");
        customerData.put("email", "JohnDoe@gamil.com");
        JSONObject ideaData = new JSONObject();
        ideaData.put("district", 1);
        ideaData.put("name", "Library");
        ideaData.put("type", 7);
        double[] result = AgentThread.test(ideaData, customerData);

        JSONObject[] output = CustomerSystemInterfaceBehaviour.handleApplicationMessageTest();

        assertAll(() -> assertEquals(ideaData.getInt("district"), output[0].getInt("district")),
                () -> assertEquals(ideaData.getInt("type"), output[0].getInt("type")),
                () -> assertEquals(ideaData.getString("name"), output[0].getString("name")));

        String idString = output[0].getString("id");

        //String test="aa-aa";

        Pattern pattern = Pattern.compile(".+-.+-.+-.+-.+");
        Matcher matcher = pattern.matcher(idString);

        assertEquals(true,matcher.matches());
    }
}