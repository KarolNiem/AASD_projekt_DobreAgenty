package com.dobreagenty;

import com.dobreagenty.behaviours.CustomerBehaviour;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackendIntegrationTest {


    @Test
    @DisplayName("Correct evaluation values received from customer")
    void inputTest() {
        JSONObject customerData = new JSONObject();
        customerData.put("phoneNumber", "123456789");
        customerData.put("surname", "Doe");
        customerData.put("name", "John");
        customerData.put("email", "JohnDoe@gamil.com");
        JSONObject ideaData = new JSONObject();
        ideaData.put("district", 2);
        ideaData.put("name", "ParkingLot");
        ideaData.put("type", 4);
        double[] result = AgentThread.test(ideaData, customerData);
        Object e=CustomerBehaviour.actionTest();
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
        double value=0;
        while (matcher.find()) {
            value = Double.parseDouble(matcher.group(1));
        }
        assertEquals(result[0], value);
        while (matcher2.find()) {
            value = Double.parseDouble(matcher2.group(1));
        }
        assertEquals(result[1], value);
        while (matcher3.find()) {
            value = Double.parseDouble(matcher3.group(1));
        }
        assertEquals(result[2], value);
        while (matcher4.find()) {
            value = Double.parseDouble(matcher4.group(1));
        }
        assertEquals(result[3], value);
        while (matcher5.find()) {
            value = Double.parseDouble(matcher5.group(1));
        }
        assertEquals(result[4], value);

    }

    @Test
    @DisplayName("0-score investment test")
    void score0Test() {

        JSONObject customerData = new JSONObject();
        customerData.put("phoneNumber", "123456789");
        customerData.put("surname", "Doe");
        customerData.put("name", "John");
        customerData.put("email", "JohnDoe@gamil.com");
        JSONObject ideaData = new JSONObject();
        ideaData.put("district", 5);
        ideaData.put("name", "ParkingLot");
        ideaData.put("type", 8);
        double[] result = AgentThread.test(ideaData, customerData);

        assertEquals(0.0,result[0]);
        assertEquals(0.0,result[1]);
        assertEquals(0.0,result[2]);
        assertEquals(0.0,result[3]);
        assertEquals(0.0,result[4]);
    }

    @Test
    @DisplayName("1-score investment test")
    void score1Test(){

        JSONObject customerData = new JSONObject();
        customerData.put("phoneNumber", "123456789");
        customerData.put("surname", "Doe");
        customerData.put("name", "John");
        customerData.put("email", "JohnDoe@gamil.com");
        JSONObject ideaData = new JSONObject();
        ideaData.put("district", 5);
        ideaData.put("name", "ParkingLot");
        ideaData.put("type", 9);
        double[] result = AgentThread.test(ideaData, customerData);

        assertEquals(1.0,result[0]);
        assertEquals(1.0,result[1]);
        assertEquals(1.0,result[2]);
        assertEquals(1.0,result[3]);
        assertEquals(1.0,result[4]);
    }
    @Test
    @DisplayName("0.5-uability score investment test")
    void usabilityScore05Test() {

        JSONObject customerData = new JSONObject();
        customerData.put("phoneNumber", "123456789");
        customerData.put("surname", "Doe");
        customerData.put("name", "John");
        customerData.put("email", "JohnDoe@gamil.com");
        JSONObject ideaData = new JSONObject();
        ideaData.put("district", 6);
        ideaData.put("name", "ParkingLot");
        ideaData.put("type", 7);
        double[] result = AgentThread.test(ideaData, customerData);
        Object e=CustomerBehaviour.actionTest();
        String str = e.toString();
        Pattern pattern = Pattern.compile("\"result\":(.*?),", Pattern.DOTALL);
        Pattern pattern2 = Pattern.compile("\"costResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern3 = Pattern.compile("\"ageStructResult\":(.*?),", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(str);
        Matcher matcher2 = pattern2.matcher(str);
        Matcher matcher3 = pattern3.matcher(str);
        String stringValue=null;
        while (matcher.find()) {
            double value = Double.parseDouble(matcher.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,587", stringValue);
        while (matcher2.find()) {
            double value = Double.parseDouble(matcher2.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,795", stringValue);
        while (matcher3.find()) {
            double value = Double.parseDouble(matcher3.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,466", stringValue);

        assertEquals(0.5,result[3]);
        assertEquals(1.0,result[4]);
    }
    @Test
    @DisplayName("Too expensive investment test")
    void tooExpensiveTest() {

        JSONObject customerData = new JSONObject();
        customerData.put("phoneNumber", "123456789");
        customerData.put("surname", "Doe");
        customerData.put("name", "John");
        customerData.put("email", "JohnDoe@gamil.com");
        JSONObject ideaData = new JSONObject();
        ideaData.put("district", 1);
        ideaData.put("name", "ParkingLot");
        ideaData.put("type", 10);
        double[] result = AgentThread.test(ideaData, customerData);
        Object e=CustomerBehaviour.actionTest();
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
        String stringValue=null;
        while (matcher.find()) {
            double value = Double.parseDouble(matcher.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,000", stringValue);
        while (matcher2.find()) {
            double value = Double.parseDouble(matcher2.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,000", stringValue);
        while (matcher3.find()) {
            double value = Double.parseDouble(matcher3.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,289", stringValue);
        while (matcher4.find()) {
            double value = Double.parseDouble(matcher4.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,613", stringValue);
        while (matcher5.find()) {
            double value = Double.parseDouble(matcher5.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,000", stringValue);
    }



    @Test
    @DisplayName("Regular investment test")
    void regularTest() {

        JSONObject customerData = new JSONObject();
        customerData.put("phoneNumber", "123456789");
        customerData.put("surname", "Doe");
        customerData.put("name", "John");
        customerData.put("email", "JohnDoe@gamil.com");
        JSONObject ideaData = new JSONObject();
        ideaData.put("district", 3);
        ideaData.put("name", "ParkingLot");
        ideaData.put("type", 2);
        double[] result = AgentThread.test(ideaData, customerData);
        Object e=CustomerBehaviour.actionTest();
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
        String stringValue=null;
        while (matcher.find()) {
            double value = Double.parseDouble(matcher.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,579", stringValue);
        while (matcher2.find()) {
            double value = Double.parseDouble(matcher2.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,933", stringValue);
        while (matcher3.find()) {
            double value = Double.parseDouble(matcher3.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,310", stringValue);
        while (matcher4.find()) {
            double value = Double.parseDouble(matcher4.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,494", stringValue);
        while (matcher5.find()) {
            double value = Double.parseDouble(matcher5.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("1,000", stringValue);
    }
}