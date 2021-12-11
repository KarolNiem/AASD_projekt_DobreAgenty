package com.dobreagenty.agents;

import com.dobreagenty.behaviours.CustomerBehaviour;
import jade.core.Agent;

public class CustomerAgent extends Agent {
    protected void setup() {
        addBehaviour(new CustomerBehaviour());
        System.out.println("Customer created.");
    }
}
