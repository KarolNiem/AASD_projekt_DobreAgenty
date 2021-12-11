package com.dobreagenty.agents;

import com.dobreagenty.behaviours.AgeStructEvaluatorBehaviour;
import jade.core.Agent;

public class AgeStructEvaluatorAgent extends Agent {
    protected void setup() {
        addBehaviour(new AgeStructEvaluatorBehaviour());
        System.out.println("AgeStructEvaluator created.");
    }
}
