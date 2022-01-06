package com.dobreagenty.agents;

import com.dobreagenty.behaviours.UsabilityEvaluatorBehaviour;
import jade.core.Agent;

public class UsabilityEvaluatorAgent extends Agent {
    protected void setup() {
        addBehaviour(new UsabilityEvaluatorBehaviour());
        System.out.println("UsabilityEvaluator created.");
    }

    protected void takeDown() {
        System.out.println("UsabilityEvaluator dies.");
        doDelete();
    }
}
