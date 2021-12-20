package com.dobreagenty.behaviours;

import jade.core.behaviours.CyclicBehaviour;

public class CustomerBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
            public ArrayList<Idea> ideas = new ArrayList<>();
    public ArrayList<CustomerDetails> customers = new ArrayList<>();

    @Override
    public void action() {
        try {
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                System.out.println("Customer received: " + msg.getContent());
                String senderName = msg.getSender().getName();
                if (senderName=="CustomerHandler") handleCustomerHandlerMessage(msg);
                else handleApplicationMessage(msg);
            } else {
                block();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleCustomerHandlerMessage(ACLMessage msg) {
        String content = msg.getContent();

        //Nie jestem pewien tak tego stringa/jsona przesłać do aplikacji
        /*ACLMessage newMsg = new ACLMessage(ACLMessage.REQUEST);
        newMsg.addReceiver(new AID("Application", AID.ISLOCALNAME));
        newMsg.setContent(content);
        myAgent.send(newMsg);
        ???*/
    }

    public void handleApplicationMessage(ACLMessage msg) {
        String content = msg.getContent(); //zakładam, że dane z aplikacji otrzymujemy jako zbiorczy string
        //zawierający dane o kliencie i pomyśle. Format stringa to ''objectData-customerData''
        String[] parts = content.split("-");
        String object=parts[0];
        String user=parts[1];
        JSONObject jsonObject = new JSONObject(object);
        JSONObject jsonUser = new JSONObject(user);

        Idea idea = new Idea(jsonObject);
        ideas.add(idea);
        CustomerDetails customerDetails = new CustomerDetails(jsonUser);
        customers.add(customerDetails);

        ACLMessage newMsgIdea = new ACLMessage(ACLMessage.REQUEST);
        newMsgIdea.addReceiver(new AID("CustomerHandler", AID.ISLOCALNAME));
        newMsgIdea.setContent(object);
        myAgent.send(newMsgIdea);

        ACLMessage newMsgUser = new ACLMessage(ACLMessage.REQUEST);
        newMsgUser.addReceiver(new AID("CustomerHandler", AID.ISLOCALNAME));
        newMsgUser.setContent(user);
        myAgent.send(newMsgUser);

        //Nie wiem jak się zachowa program kiedy wysyłamy szeregowo 2 różne wiadomości do tego samego odbiorcy
        //dlatego alternatywnie można będzie przesłać cały ''content'' do customerHandlera i dopiero tam
        //rozbić go na 2 stringi
    }
    }
}
