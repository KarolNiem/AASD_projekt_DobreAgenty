package com.dobreagenty;

import com.dobreagenty.behaviours.CustomerBehaviour;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CustomerAgentUnitTests {


    @Test
    @DisplayName("Customer agent correct data flow and processing tests")
    void customerTests() {
        JSONObject customerData = new JSONObject();
        customerData.put("phoneNumber", "123456789");
        customerData.put("surname", "Doe");
        customerData.put("name", "John");
        customerData.put("email", "JohnDoe@gamil.com");
        JSONObject ideaData = new JSONObject();
        ideaData.put("district", 1);
        ideaData.put("name", "Library");
        ideaData.put("type", 7);
        double[] result = AgentThread.test(ideaData, customerData);



        JSONObject[] output = CustomerBehaviour.handleApplicationMessageTest();
        assertAll(() -> assertEquals(ideaData.getInt("district"), output[0].getInt("district")),
                () -> assertEquals(ideaData.getInt("type"), output[0].getInt("type")),
                () -> assertEquals(ideaData.getString("name"), output[0].getString("name")),
                () -> assertEquals(customerData.getString("phoneNumber"), output[1].getString("phoneNumber")),
                () -> assertEquals(customerData.getString("name"), output[1].getString("name")),
                () -> assertEquals(customerData.getString("surname"), output[1].getString("surname")),
                () -> assertEquals(customerData.getString("email"), output[1].getString("email")));



        Object e=CustomerBehaviour.actionTest();
        String str = e.toString();
        Pattern pattern = Pattern.compile("\"result\":(.*?),", Pattern.DOTALL);
        Pattern pattern2 = Pattern.compile("\"costResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern3 = Pattern.compile("\"ageStructResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern4 = Pattern.compile("\"usabilityResult\":(.*?),", Pattern.DOTALL);
        Pattern pattern5 = Pattern.compile("\"budgetResult\":(.*?)}", Pattern.DOTALL);
        Pattern pattern6 = Pattern.compile("\"district\":(.*?),", Pattern.DOTALL);
        Pattern pattern7 = Pattern.compile("\"name\":\"(.*?)\"", Pattern.DOTALL);
        Pattern pattern8 = Pattern.compile("\"type\":(.*?),", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(str);
        Matcher matcher2 = pattern2.matcher(str);
        Matcher matcher3 = pattern3.matcher(str);
        Matcher matcher4 = pattern4.matcher(str);
        Matcher matcher5 = pattern5.matcher(str);
        Matcher matcher6 = pattern6.matcher(str);
        Matcher matcher7 = pattern7.matcher(str);
        Matcher matcher8 = pattern8.matcher(str);
        String stringValue=null;
        while (matcher.find()) {
            double value = Double.parseDouble(matcher.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,647", stringValue);
        while (matcher2.find()) {
            double value = Double.parseDouble(matcher2.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,848", stringValue);
        while (matcher3.find()) {
            double value = Double.parseDouble(matcher3.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,489", stringValue);
        while (matcher4.find()) {
            double value = Double.parseDouble(matcher4.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("0,603", stringValue);
        while (matcher5.find()) {
            double value = Double.parseDouble(matcher5.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("1,000", stringValue);
        while (matcher6.find()) {
            double value = Double.parseDouble(matcher6.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("1,000", stringValue);
        while (matcher7.find()) {
            stringValue=matcher7.group(1);
        }
        assertEquals("Library", stringValue);
        while (matcher8.find()) {
            double value = Double.parseDouble(matcher8.group(1));
            stringValue=String.format("%.3f", value);
        }
        assertEquals("7,000", stringValue);
    }

}