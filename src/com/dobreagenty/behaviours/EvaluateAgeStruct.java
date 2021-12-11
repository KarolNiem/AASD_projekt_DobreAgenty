package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.AgeStructEvaluation;
import com.dobreagenty.payloads.Offer;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class EvaluateAgeStruct extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                String content = msg.getContent();
                JSONObject json = new JSONObject(content);
                Offer offer = new Offer(json);
                AgeStructEvaluation evaluation = new AgeStructEvaluation(offer);
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent(evaluation.toString());
                myAgent.send(reply);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}
