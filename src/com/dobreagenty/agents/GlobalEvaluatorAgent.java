package com.dobreagenty.agents;

import com.dobreagenty.behaviours.GlobalEvaluatorBehaviour;
import jade.core.Agent;

public class GlobalEvaluatorAgent extends Agent {
    protected void setup() {
        addBehaviour(new GlobalEvaluatorBehaviour());
        System.out.println("GlobalEvaluator created.");
    }
}
