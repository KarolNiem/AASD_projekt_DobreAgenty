package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.BaseEvaluation;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public abstract class EvaluatorBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
    }

    protected void reply(ACLMessage msg, BaseEvaluation evaluation) {
        ACLMessage reply = msg.createReply();
        reply.setPerformative(ACLMessage.INFORM);
        reply.setContent(evaluation.toString());
        myAgent.send(reply);
    }
}
