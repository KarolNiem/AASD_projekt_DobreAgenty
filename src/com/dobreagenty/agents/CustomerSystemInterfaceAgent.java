package com.dobreagenty.agents;

import com.dobreagenty.behaviours.CommunicateWithCostEvaluator;
import jade.core.Agent;

public class CustomerSystemInterfaceAgent extends Agent {
    protected void setup() {
        addBehaviour(new CommunicateWithCostEvaluator());
        System.out.println("CustomerSystemInterface created.");
    }
}
