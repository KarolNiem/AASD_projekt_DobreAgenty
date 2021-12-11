package com.dobreagenty.agents;

import com.dobreagenty.behaviours.EvaluateCost;
import jade.core.Agent;

public class CostEvaluatorAgent extends Agent {
    protected void setup() {
        addBehaviour(new EvaluateCost());
    }
}
