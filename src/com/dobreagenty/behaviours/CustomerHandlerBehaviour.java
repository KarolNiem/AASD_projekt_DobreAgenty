package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.BaseEvaluation;
import com.dobreagenty.payloads.CostEvaluation;
import com.dobreagenty.payloads.Idea;
import com.dobreagenty.payloads.Offer;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class CustomerHandlerBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        //Uwaga dla Jacka, ten kod możesz wywalić, zrobiłem go żeby przetestować komunikację
        //Do CustomHandlera wrzucam z Customera 2 wiadomości: Najpierw Idea, z którego trzeba będzie zrobić Offer
        // a później customerDetails, które będzie potrzebne przy aktualizacji bazy danych
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                String content = msg.getContent();
                System.out.println("CustomerHandler received: " + content);
                content =content + "-RETURN";
                ACLMessage newMsgIdea = new ACLMessage(ACLMessage.REQUEST);
                newMsgIdea.addReceiver(new AID("Customer", AID.ISLOCALNAME));
                newMsgIdea.setContent(content);
                myAgent.send(newMsgIdea);
                myAgent.doWait(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
    protected void reply(ACLMessage msg, String evaluation) {
        ACLMessage reply = msg.createReply();
        reply.setPerformative(ACLMessage.INFORM);
        reply.setContent(evaluation);
        myAgent.send(reply);
    }
}
