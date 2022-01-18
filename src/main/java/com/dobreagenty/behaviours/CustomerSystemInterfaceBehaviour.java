package com.dobreagenty.behaviours;

import com.dobreagenty.payloads.*;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import com.dobreagenty.payloads.Offer;

public class CustomerSystemInterfaceBehaviour extends CyclicBehaviour {
    public ArrayList<Offer> offers = new ArrayList<>();
    public ArrayList<EvaluationSummary> summaries = new ArrayList<>();

    @Override
    public void action() {
        try {
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                String content = msg.getContent();

                System.out.println("CustomerSystemInterface received: " + msg.getContent()+" from "+msg.getSender().getName());
                
                JSONObject customerIdea =new JSONObject(content);

                String senderName = msg.getSender().getName();
                String[] parts = senderName.split("@");
                String senderNameString=parts[0];
                switch (senderNameString) {
                    case "Customer" -> handleCustomerMessage(msg);
                    case "CostEvaluator" -> handleCostEvaluatorReply(msg);
                    case "AgeStructEvaluator" -> handleAgeStructEvaluatorReply(msg);
                    case "UsabilityEvaluator" -> handleUsabilityEvaluatorReply(msg);
                    case "BudgetChecker" -> handleBudgetCheckerReply(msg);
                    case "GlobalEvaluator" -> handleGlobalEvaluatorReply(msg);
                }

            } else {
                block();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleCustomerMessage(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);

        Offer offer = new Offer(json, true);
        offers.add(offer);
        EvaluationSummary summary = new EvaluationSummary(offer);
        summaries.add(summary);
        content = offer.toString();

        ACLMessage newMsg = new ACLMessage(ACLMessage.REQUEST);
        newMsg.addReceiver(new AID("CostEvaluator", AID.ISLOCALNAME));
        newMsg.addReceiver(new AID("AgeStructEvaluator", AID.ISLOCALNAME));
        newMsg.addReceiver(new AID("UsabilityEvaluator", AID.ISLOCALNAME));
        newMsg.addReceiver(new AID("BudgetChecker", AID.ISLOCALNAME));
        newMsg.setContent(content);
        myAgent.send(newMsg);
    }

    public void handleCostEvaluatorReply(ACLMessage msg) {

        String content = msg.getContent();
        JSONObject json = new JSONObject(content);
        CostEvaluation evaluation = new CostEvaluation(json);
        EvaluationSummary summary = findSummaryWithID(evaluation.offer.id);
        if (summary != null) {
            summary.costEvaluation = evaluation.result;
            if (summary.isCompleted()) {
                sendSummaryToGlobalEvaluator(summary);
            }
        }
    }

    private void handleAgeStructEvaluatorReply(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);
        AgeStructEvaluation evaluation = new AgeStructEvaluation(json);
        EvaluationSummary summary = findSummaryWithID(evaluation.offer.id);
        if (summary != null) {
            summary.ageStructEvaluation = evaluation.result;
            if (summary.isCompleted()) {
                sendSummaryToGlobalEvaluator(summary);
            }
        }
    }

    private void handleUsabilityEvaluatorReply(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);
        UsabilityEvaluation evaluation = new UsabilityEvaluation(json);
        EvaluationSummary summary = findSummaryWithID(evaluation.offer.id);
        if (summary != null) {
            summary.usabilityEvaluation = evaluation.result;
            if (summary.isCompleted()) {
                sendSummaryToGlobalEvaluator(summary);
            }
        }
    }

    private void handleBudgetCheckerReply(ACLMessage msg) {
        String content = msg.getContent();
        JSONObject json = new JSONObject(content);
        BudgetEvaluation evaluation = new BudgetEvaluation(json);
        EvaluationSummary summary = findSummaryWithID(evaluation.offer.id);
        if (summary != null) {
            summary.budgetEvaluation = evaluation.result;
            if (summary.isCompleted()) {
                sendSummaryToGlobalEvaluator(summary);
            }
        }
    }

    private void handleGlobalEvaluatorReply(ACLMessage msg) {
        msg.addReceiver(new AID("Customer", AID.ISLOCALNAME));
        msg.setContent(msg.getContent());
        myAgent.send(msg);
        myAgent.blockingReceive();
    }

    private void sendSummaryToGlobalEvaluator(EvaluationSummary summary) {
        offers.removeIf(o -> o.id == summary.offer.id);
        summaries.remove(summary);

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("GlobalEvaluator", AID.ISLOCALNAME));
        msg.setContent(summary.toString());
        myAgent.send(msg);
    }

    private EvaluationSummary findSummaryWithID(UUID id) {
        return  summaries.stream()
                .filter(s -> Objects.equals(s.offer.id.toString(), id.toString()))
                .findFirst()
                .orElse(null);
    }
}
