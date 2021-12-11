package com.dobreagenty.agents;

import com.dobreagenty.behaviours.EvaluateUsability;
import jade.core.Agent;

public class UsabilityEvaluatorAgent extends Agent {
    protected void setup() {
        addBehaviour(new EvaluateUsability());
    }
}
