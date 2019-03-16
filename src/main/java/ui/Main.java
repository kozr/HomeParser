package ui;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.twilio.Twilio;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static final String ACCOUNT_SID = "AC1c1a5e23709429cc3f5c90217b31dcaa";
    public static final String AUTH_TOKEN = "e499466ce5bbf51126404bca4207cb96";
    public static SMSParser parserSMS;
    public static final GpioController gpio = GpioFactory.getInstance();

    public static void main(String[] args) {
        parserSMS = new SMSParser();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        get("/", (req, res) -> "Hello Web");

        post("/sms", (req, res) -> {
            System.out.println(req.queryMap("Body").value());
            res.type("application/xml");

            try {
                parserSMS.parse(req.queryMap("Body").value());
            } catch (IllegalArgumentException e) {Body body = new Body
                    .Builder(e.getMessage())
                    .build();
                Message sms = new Message
                        .Builder()
                        .body(body)
                        .build();
                MessagingResponse twiml = new MessagingResponse
                        .Builder()
                        .message(sms)
                        .build();
                System.out.println(e.getMessage());
                return twiml.toXml();
            }
            Body body = new Body
                    .Builder("Success!")
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        });

    }


}
