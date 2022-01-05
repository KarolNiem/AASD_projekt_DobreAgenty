package com.dobreagenty.agents;

import com.dobreagenty.behaviours.CostEvaluatorBehaviour;
import jade.core.Agent;

public class CostEvaluatorAgent extends Agent {
    protected void setup() {
        addBehaviour(new CostEvaluatorBehaviour());
        System.out.println("CostEvaluator created.");
    }

    protected void takeDown() {
        System.out.println("CostEvaluator dies.");
        doDelete();
    }
}
