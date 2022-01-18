package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.Evaluation;
import com.dobreagenty.payloads.EvaluationSummary;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class GlobalEvaluatorBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        try {
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                Evaluation evaluation = evaluate(msg);
                sendEvaluationToCustomerHandler(evaluation);
            } else {
                block();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Evaluation evaluate(ACLMessage msg) {
        String content = msg.getContent();
        System.out.println("GlobalEvaluator received: " + content);
        JSONObject json = new JSONObject(content);
        EvaluationSummary summary = new EvaluationSummary(json);
        return new Evaluation(summary);
    }

    private void sendEvaluationToCustomerHandler(Evaluation evaluation) {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("CustomerSystemInterface", AID.ISLOCALNAME));
            msg.setContent(evaluation.toString());
            myAgent.send(msg);
    }
}
