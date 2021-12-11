package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.*;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.UUID;

public class CustomerSystemInterfaceBehaviour extends CyclicBehaviour {
    public ArrayList<Offer> offers = new ArrayList<>();
    public ArrayList<EvaluationSummary> summaries = new ArrayList<>();

    @Override
    public void action() {
        try {
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                System.out.println("CustomerSystemInterface received: " + msg.getContent());
                String senderName = msg.getSender().getName();
                switch (senderName) {
                    case "Customer" -> HandleCustomerMessage(msg);
                    case "CostEvaluator" -> HandleCostEvaluatorReply(msg);
                    case "AgeStructEvaluator" -> HandleAgeStructEvaluatorReply(msg);
                    case "UsabilityEvaluator" -> HandleUsabilityEvaluatorReply(msg);
                    case "BudgetChecker" -> HandleBudgetCheckerReply(msg);
                }
            } else {
                block();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HandleCustomerMessage(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);

        Offer offer = new Offer(json);
        offers.add(offer);
        EvaluationSummary summary = new EvaluationSummary(offer);
        summaries.add(summary);

        ACLMessage newMsg = new ACLMessage(ACLMessage.REQUEST);
        newMsg.addReceiver(new AID("CostEvaluator", AID.ISLOCALNAME));
        newMsg.addReceiver(new AID("AgeStructEvaluator", AID.ISLOCALNAME));
        newMsg.addReceiver(new AID("UsabilityEvaluator", AID.ISLOCALNAME));
        newMsg.addReceiver(new AID("BudgetChecker", AID.ISLOCALNAME));
        newMsg.setContent(content);
        myAgent.send(newMsg);
    }

    public void HandleCostEvaluatorReply(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);
        CostEvaluation evaluation = new CostEvaluation(json);
        EvaluationSummary summary = FindSummaryWithID(evaluation.offerID);
        if (summary != null) {
            summary.costEvaluation = evaluation;
            if (summary.IsCompleted()) {
                SendEvaluationToCustomerHandler(summary);
            }
        }
    }

    private void HandleAgeStructEvaluatorReply(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);
        AgeStructEvaluation evaluation = new AgeStructEvaluation(json);
        EvaluationSummary summary = FindSummaryWithID(evaluation.offerID);
        if (summary != null) {
            summary.ageStructEvaluation = evaluation;
            if (summary.IsCompleted()) {
                SendEvaluationToCustomerHandler(summary);
            }
        }
    }

    private void HandleUsabilityEvaluatorReply(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);
        UsabilityEvaluation evaluation = new UsabilityEvaluation(json);
        EvaluationSummary summary = FindSummaryWithID(evaluation.offerID);
        if (summary != null) {
            summary.usabilityEvaluation = evaluation;
            if (summary.IsCompleted()) {
                SendEvaluationToCustomerHandler(summary);
            }
        }
    }

    private void HandleBudgetCheckerReply(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);
        BudgetEvaluation evaluation = new BudgetEvaluation(json);
        EvaluationSummary summary = FindSummaryWithID(evaluation.offerID);
        if (summary != null) {
            summary.budgetEvaluation = evaluation;
            if (summary.IsCompleted()) {
                SendEvaluationToCustomerHandler(summary);
            }
        }
    }

    private void SendEvaluationToCustomerHandler(EvaluationSummary summary) {
        offers.removeIf(o -> o.id == summary.offerID);
        summaries.remove(summary);

        Evaluation globalEvaluation = new Evaluation();
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("CustomerHandler", AID.ISLOCALNAME));
        msg.setContent(globalEvaluation.toString());
        myAgent.send(msg);
    }

    private EvaluationSummary FindSummaryWithID(UUID id) {
        return summaries.stream()
                .filter((s) -> s.offerID == id)
                .findFirst()
                .orElse(null);
    }
}
