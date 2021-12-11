package com.dobreagenty.agents;

import com.dobreagenty.behaviours.EvaluateAgeStruct;
import jade.core.Agent;

public class AgeStructEvaluatorAgent extends Agent {
    protected void setup() {
        addBehaviour(new EvaluateAgeStruct());
    }
}
