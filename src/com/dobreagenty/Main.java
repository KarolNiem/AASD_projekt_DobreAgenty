package com.dobreagenty;

import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
            Profile profile = new ProfileImpl();
            AgentContainer container = Runtime.instance().createMainContainer(profile);
            //Przykładowy format danych na wejściu do Customer
        JSONObject exampleObject=new JSONObject();
        JSONObject exampleCustomer=new JSONObject();
        //JSON Idea
        exampleObject.put("name","parking");
        exampleObject.put("type",4);
        exampleObject.put("district",2);
        //JSON customerDetails
        exampleCustomer.put("name","Johnny");
        exampleCustomer.put("surname","Silverhand");
        exampleCustomer.put("email","JS@gmail.com");
        exampleCustomer.put("phoneNumber","123");
        //W docelowej wersji, jak będziemy otrzymywać faktyczne wyniki przerobię output na double,
        //póki co do testów jest stringiem
        String input = exampleObject.toString()+"-"+exampleCustomer.toString();
        StringBuilder output = new StringBuilder ();
        output.append("");
            try {
                createAgents(container, input,output);
            } catch (StaleProxyException | InterruptedException e) {
                e.printStackTrace();
            }
        }


        private static void createAgents(AgentContainer container, String ez, StringBuilder e) throws StaleProxyException, InterruptedException {

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
            //customerHandler.start();
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
            customer.kill();
            //customerSystemInterface.kill();
            //customer.kill();
            //customerSystemInterface.start();
            //costEvaluator.start();
            //usabilityEvaluator.start();
            //ageStructEvaluator.start();
            //budgetChecker.start();
            //globalEvaluator.start();
        }
}
