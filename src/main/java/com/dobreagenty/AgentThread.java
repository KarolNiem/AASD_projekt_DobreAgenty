package com.dobreagenty;

import com.dobreagenty.aasd_javafx_demo.EvaluationListener;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgentThread extends Thread {

    private final JSONObject idea;
    private final JSONObject customer;
    private static double evaluation;
    private final EvaluationListener listener;

    public AgentThread(JSONObject ideaData, JSONObject customerData, EvaluationListener listenerData){
        this.idea = ideaData;
        this.customer = customerData;
        this.listener = listenerData;
    }
    public void run(){
        setUpAgentEnvironment(idea, customer);
    }

    public void setUpAgentEnvironment(JSONObject ideaObject, JSONObject customerObject) {
        Profile profile = new ProfileImpl();
        AgentContainer container = Runtime.instance().createMainContainer(profile);

        String input = ideaObject.toString()+"-"+customerObject.toString();
        StringBuilder output = new StringBuilder ();
        output.append("");
        try {
            createAgents(container, input, output);
        } catch (StaleProxyException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static double getEvaluationValue(){
        return evaluation;
    }

    private void createAgents(AgentContainer container, String ez, StringBuilder e) throws StaleProxyException, InterruptedException {

        Object[] objects = new Object[]{
                ez,
                e,
        };

        AgentController customer = container.createNewAgent("Customer",
                "com.dobreagenty.agents.CustomerAgent", objects);
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
        customerSystemInterface.start();
        costEvaluator.start();
        ageStructEvaluator.start();
        usabilityEvaluator.start();
        budgetChecker.start();
        globalEvaluator.start();
        TimeUnit.SECONDS.sleep(1);
        String singleString = e.toString();
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
        Matcher m = p.matcher(singleString);
        double d = 0;
        while(m.find()) {
            d = Double.parseDouble(m.group(1));
        }
        System.out.println("Customer output: "+d);
        evaluation = d;
        listener.onEvent();
        customer.kill();
    }
}
