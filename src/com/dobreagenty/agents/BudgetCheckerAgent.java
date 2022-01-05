package com.dobreagenty.agents;

import com.dobreagenty.behaviours.BudgetCheckerBehaviour;
import jade.core.Agent;

public class BudgetCheckerAgent extends Agent {
    protected void setup() {
        addBehaviour(new BudgetCheckerBehaviour());
        System.out.println("BudgetChecker created.");
    }

    protected void takeDown() {
        System.out.println("BudgetChecker dies.");
        doDelete();
    }
}
