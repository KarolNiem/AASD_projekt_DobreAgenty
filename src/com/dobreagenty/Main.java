package com.dobreagenty;

import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Main {

    public static void main(String[] args) {
        Profile profile = new ProfileImpl();
        AgentContainer container = Runtime.instance().createMainContainer(profile);
        try {
            createAgents(container);
        }
        catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    private static void createAgents(AgentContainer container) throws StaleProxyException {
        AgentController customer = container.createNewAgent("Customer",
                "com.dobreagenty.agents.CustomerAgent", null);
        AgentController customerHandler = container.createNewAgent("CustomerHandler",
                "com.dobreagenty.agents.CustomerHandlerAgent", null);
        AgentController customerSystemInterface = container.createNewAgent("CustomerSystemInterface",
                "com.dobreagenty.agents.CustomerSystemInterfaceAgent", null);
        AgentController costEvaluator = container.createNewAgent("CostEvaluator",
                "com.dobreagenty.agents.CostEvaluatorAgent", null);
        AgentController usabilityEvaluator = container.createNewAgent("UsabilityEvaluator",
                "com.dobreagenty.agents.UsabilityEvaluatorAgent", null);
        AgentController ageStructEvaluator = container.createNewAgent("AgeStructEvaluator",
                "com.dobreagenty.agents.AgeStructEvaluatorAgent", null);
        AgentController budgetChecker = container.createNewAgent("BudgetChecker",
                "com.dobreagenty.agents.BudgetCheckerAgent", null);
        AgentController globalEvaluator = container.createNewAgent("GlobalEvaluator",
                "com.dobreagenty.agents.GlobalEvaluatorAgent", null);

        customer.start();
        customerHandler.start();
        customerSystemInterface.start();
        costEvaluator.start();
        usabilityEvaluator.start();
        ageStructEvaluator.start();
        budgetChecker.start();
        globalEvaluator.start();
    }
}
