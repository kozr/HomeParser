package ui;

import com.twilio.Twilio;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import parser.SMSParser;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static final String ACCOUNT_SID = "AC1c1a5e23709429cc3f5c90217b31dcaa";
    public static final String AUTH_TOKEN = "9b53ea7f70a750552c0a1729ad343016";
    public static parser.SMSParser parserSMS;

    public static void main(String[] args) {
        parserSMS = new SMSParser();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        get("/", (req, res) -> "Hello Web");

        post("/sms", (req, res) -> {
            System.out.println(req.body());
            req.body();
            res.type("application/xml");
            Body body = new Body
                    .Builder("The Robots are coming! Head for the hills!")
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            try {
                parserSMS.parse(req.body());
            } catch (IllegalArgumentException e) {

            }
            return twiml.toXml();
        });

    }


}
