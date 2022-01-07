package com.dobreagenty.agents;

import com.dobreagenty.behaviours.CostEvaluatorBehaviour;
import com.dobreagenty.behaviours.CustomerBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;
import com.dobreagenty.misc.OfferTypeEnum;
import com.dobreagenty.payloads.*;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.persistence.DeleteAgent;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class CustomerAgent extends Agent {
    protected void setup() {
        System.out.println("Customer created.");
        addBehaviour(new CustomerBehaviour());

        Object[] args = getArguments();
        if (args == null && args.length == 0) {
            System.out.println("No arguments specified");
            doDelete();
        }
    }

    protected void takeDown() {
        System.out.println("Customer dies.");
        doDelete();
    }
}
