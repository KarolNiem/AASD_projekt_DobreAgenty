package com.dobreagenty.behaviours;

import com.dobreagenty.misc.DistrictEnum;
import com.dobreagenty.misc.OfferType;
import com.dobreagenty.misc.OfferTypeEnum;
import com.dobreagenty.payloads.*;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.persistence.DeleteAgent;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;
import jade.core.Agent;

import java.util.ArrayList;

public class CustomerBehaviour extends OneShotBehaviour {
    public static ArrayList<Idea> ideas = new ArrayList<>();
    public static ArrayList<CustomerDetails> customers = new ArrayList<>();
    static Object arg=null;
    static JSONObject[] PreprocessedData= new JSONObject[2];

    @Override
    public void action() {

        Object[] args = myAgent.getArguments();

        String input = (String) args[0];
        System.out.println("Customer input: " + input);

        handleApplicationMessage(input);

        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                String contentReceived = msg.getContent();
                System.out.println("Customer received: " + contentReceived);
                args[1] = ((StringBuilder) args[1]).append(contentReceived);
                arg=args[1];
                actionTest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }


    public void handleApplicationMessage(String content) {
        String[] parts = content.split("-");
        String object = parts[0];
        String user = parts[1];
        JSONObject jObject = new JSONObject(object);
        JSONObject jUser = new JSONObject(user);
        PreprocessedData[0]=jObject;
        PreprocessedData[1]=jUser;
        handleApplicationMessageTest();


        Idea idea = new Idea(jObject);
        ideas.add(idea);
        CustomerDetails customerDetails = new CustomerDetails(jUser);
        customers.add(customerDetails);

        ACLMessage newMsgIdea = new ACLMessage(ACLMessage.REQUEST);
        newMsgIdea.addReceiver(new AID("customerSystemInterface", AID.ISLOCALNAME));
        newMsgIdea.setContent(object);
        myAgent.send(newMsgIdea);
        myAgent.doWait(200);

        //ACLMessage newMsgUser = new ACLMessage(ACLMessage.REQUEST);
        //newMsgUser.addReceiver(new AID("customerSystemInterface", AID.ISLOCALNAME));
        //newMsgUser.setContent(user);
        //myAgent.send(newMsgUser);
        //myAgent.doWait(200);
    }

    public static JSONObject[] handleApplicationMessageTest() {
        return PreprocessedData;
    }

    public static Object actionTest() {
        return arg;
    }
}
