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
    private static double[] frontendStructure = new double[5];
    private static EvaluationListener listener = null;

    public AgentThread(JSONObject ideaData, JSONObject customerData, EvaluationListener listenerData) {
        this.idea = ideaData;
        this.customer = customerData;
        this.listener = listenerData;
    }

    public void run() {
        setUpAgentEnvironment(idea, customer);
    }

    public static double[] test(JSONObject ideaObject, JSONObject customerObject) {
        Profile profile = new ProfileImpl();
        AgentContainer container = Runtime.instance().createMainContainer(profile);

        String input = ideaObject.toString() + "-" + customerObject.toString();
        StringBuilder output = new StringBuilder();
        output.append("");
        try {
            createAgentsTest(container, input, output);
        } catch (StaleProxyException | InterruptedException e) {
            e.printStackTrace();
        }
        return frontendStructure;
    }

    public static void setUpAgentEnvironment(JSONObject ideaObject, JSONObject customerObject) {
        Profile profile = new ProfileImpl();
        AgentContainer container = Runtime.instance().createMainContainer(profile);

        String input = ideaObject.toString() + "-" + customerObject.toString();
        StringBuilder output = new StringBuilder();
        output.append("");
        try {
            createAgents(container, input, output);
        } catch (StaleProxyException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static double getEvaluationValue() {
        return evaluation;
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
        customerSystemInterface.start();
        costEvaluator.start();
        ageStructEvaluator.start();
        usabilityEvaluator.start();
        budgetChecker.start();
        globalEvaluator.start();
        TimeUnit.SECONDS.sleep(1);
        String singleString = e.toString();                // Te linijki będą do wywalenia jak zajdą zmiany we frontendzie
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");  //
        Matcher m = p.matcher(singleString);               //
        double d = 0;                                      //
        while (m.find()) {                                  //
            d = Double.parseDouble(m.group(1));            //
        }                                                  //
        String str = e.toString();
        Pattern pattern = Pattern.compile("\"result\":(.*?),", Pattern.DOTALL);
        Pattern pattern2 = Pattern.compile("\"costResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern3 = Pattern.compile("\"ageStructResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern4 = Pattern.compile("\"usabilityResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern5 = Pattern.compile("\"budgetResult\":(.*?)}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(str);
        Matcher matcher2 = pattern2.matcher(str);
        Matcher matcher3 = pattern3.matcher(str);
        Matcher matcher4 = pattern4.matcher(str);
        Matcher matcher5 = pattern5.matcher(str);
        while (matcher.find()) {
            frontendStructure[0] = Double.parseDouble(matcher.group(1));
        }
        while (matcher2.find()) {
            frontendStructure[1] = Double.parseDouble(matcher2.group(1));
        }
        while (matcher3.find()) {
            frontendStructure[2] = Double.parseDouble(matcher3.group(1));
        }
        while (matcher4.find()) {
            frontendStructure[3] = Double.parseDouble(matcher4.group(1));
        }
        while (matcher5.find()) {
            frontendStructure[4] = Double.parseDouble(matcher5.group(1));
        }
        System.out.println("Customer output: " + frontendStructure[0] + ", " + frontendStructure[1] + ", " + frontendStructure[2] + ", " + frontendStructure[3] + ", " + frontendStructure[4]);
        //evaluationd = frontendStructure
        evaluation = d;
        listener.onEvent();
        customer.kill();
    }

    private static void createAgentsTest(AgentContainer container, String ez, StringBuilder e) throws StaleProxyException, InterruptedException {

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
        String singleString = e.toString();                // Te linijki będą do wywalenia jak zajdą zmiany we frontendzie
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");  //
        Matcher m = p.matcher(singleString);               //
        double d = 0;                                      //
        while (m.find()) {                                  //
            d = Double.parseDouble(m.group(1));            //
        }                                                  //
        String str = e.toString();
        Pattern pattern = Pattern.compile("\"result\":(.*?),", Pattern.DOTALL);
        Pattern pattern2 = Pattern.compile("\"costResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern3 = Pattern.compile("\"ageStructResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern4 = Pattern.compile("\"usabilityResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern5 = Pattern.compile("\"budgetResult\":(.*?)}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(str);
        Matcher matcher2 = pattern2.matcher(str);
        Matcher matcher3 = pattern3.matcher(str);
        Matcher matcher4 = pattern4.matcher(str);
        Matcher matcher5 = pattern5.matcher(str);
        while (matcher.find()) {
            frontendStructure[0] = Double.parseDouble(matcher.group(1));
        }
        while (matcher2.find()) {
            frontendStructure[1] = Double.parseDouble(matcher2.group(1));
        }
        while (matcher3.find()) {
            frontendStructure[2] = Double.parseDouble(matcher3.group(1));
        }
        while (matcher4.find()) {
            frontendStructure[3] = Double.parseDouble(matcher4.group(1));
        }
        while (matcher5.find()) {
            frontendStructure[4] = Double.parseDouble(matcher5.group(1));
        }
        System.out.println("Customer output: " + frontendStructure[0] + ", " + frontendStructure[1] + ", " + frontendStructure[2] + ", " + frontendStructure[3] + ", " + frontendStructure[4]);
        //evaluationd = frontendStructure
        evaluation = d;
        //listener.onEvent();
        customer.kill();
    }
}
