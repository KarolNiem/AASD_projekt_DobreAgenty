package com.dobreagenty.agents;

import com.dobreagenty.behaviours.CustomerSystemInterfaceBehaviour;
import jade.core.Agent;

public class CustomerSystemInterfaceAgent extends Agent {
    protected void setup() {
        addBehaviour(new CustomerSystemInterfaceBehaviour());
        System.out.println("CustomerSystemInterface created.");
    }

    protected void takeDown() {
        System.out.println("CustomerSystemInterface dies.");
        doDelete();
    }
}
