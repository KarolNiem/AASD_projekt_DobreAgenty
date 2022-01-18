package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.BaseEvaluation;
import com.dobreagenty.payloads.Offer;
import com.dobreagenty.payloads.UsabilityEvaluation;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public abstract class EvaluatorBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                BaseEvaluation evaluation = evaluate(msg);
                reply(msg, evaluation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }

    protected void reply(ACLMessage msg, BaseEvaluation evaluation) {
        ACLMessage reply = msg.createReply();
        reply.setPerformative(ACLMessage.INFORM);
        reply.setContent(evaluation.toString());
        myAgent.send(reply);
    }

    public abstract BaseEvaluation evaluate(ACLMessage msg);
}
