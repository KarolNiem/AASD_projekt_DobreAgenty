package com.dobreagenty.agents;

import com.dobreagenty.behaviours.CheckBudget;
import jade.core.Agent;

public class BudgetCheckerAgent extends Agent {
    protected void setup() {
        addBehaviour(new CheckBudget());
        System.out.println("BudgetChecker created.");
    }
}
