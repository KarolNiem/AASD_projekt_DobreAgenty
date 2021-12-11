package com.dobreagenty.agents;

import com.dobreagenty.behaviours.CustomerHandlerBehaviour;
import jade.core.Agent;

public class CustomerHandlerAgent extends Agent {
    protected void setup() {
        addBehaviour(new CustomerHandlerBehaviour());
        System.out.println("CustomerHandler created.");
    }
}
