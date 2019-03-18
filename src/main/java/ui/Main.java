package ui;

import com.twilio.Twilio;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static spark.Spark.get;
import static spark.Spark.post;


public class Main {

    public static final String ACCOUNT_SID = "Hidden";
    public static final String AUTH_TOKEN = "Hidden";
    public static SMSParser parserSMS;
    public static final GpioController gpio = GpioFactory.getInstance();
    public static GpioPinDigitalOutput myLed;

    public static void main(String[] args) {
        parserSMS = new SMSParser();
        // Initializing LED at GPIO 4
        myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
                "led",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
        myLed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        parserSMS.parse("led add");
        System.out.println("LED Initialized (Blink once) " + myLed.blink(1, 100));

        // Init with Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        get("/", (req, res) -> "Nick's smart home, please kindly steer away.");

        post("/sms", (req, res) -> {
            res.type("application/xml");
            try {
                parserSMS.parse(req.queryMap("Body").value().trim());

            } catch (IllegalArgumentException e) {
                Body body = new Body
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
                    .Builder("Your " + SMSParser.getTermOne() + " is now " + SMSParser.getTermTwo())
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
