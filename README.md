# Description:
This project extends the ability of smart homes by creating an almost real time SMS to RaspberryPi connection. In this sample repo, a LED is coded in to activate from Raspberry Pi's GPIO04 pin once the SMS message "LED on" is sent to a specific phone number, and could be turned off by texting "LED off". 

# How to use:
### REQUIRED...
Please install the following prior launching the application.
* [Apache Maven](https://maven.apache.org/install.html)
* [Java SDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### OPTIONAL...
* [ngrok](https://ngrok.com) at step 7

### BASIC INSTRUCTIONS...
##### For Raspberry Pi ONLY:

1. Signup for [Twilio](https://www.twilio.com/try-twilio) and get their auth ID's after signing up for a free trial of a phone number. (Public and Private)
2. Replace the corresponding constants in src/main/java/ui/Main.java with the auth ID's.
3. Connect your LED to GPIO04 with the pin numbering [scheme](https://www.pi4j.com/1.2/pin-numbering-scheme.html)
4. Open a terminal inside the first layer of HomeParser directory and type ```mvn clean package``` followed by ```mvn install``` to create a executable .jar file inside target/
5. run ```sudo java -jar target/maven-1.0-SNAPSHOT.jar``` in the same terminal
6. A web server should now run on localhost:4567
7. Port forward or use ngrok -> In your installation directory of ngrok, run ```./ngrok http 4567```. ngrok should then give you a web address, save this.
8. On Twilio, click on your active number and go into configure, scroll all the way down to the messaging part and paste in ```http://[ngrok address from step 7]/sms ``` or ```http://[your port forwarded address]/sms```.
9. Text "LED on" or "LED off" to the Twilio active number and watch your LED turn on.

# How to modify it for your raspberry pi functions:
To be finished.

# How it works:
Example:

In short: The SMS, "LED on" sends to a Twilio phone number and they send a webhook (POST) to a Raspberry Pi's Spark web server, the Raspberry Pi at home then parse it and set output of its corresponding pin to high and lights up the LED.

# Code associated with:


-Spark

-Pi4J

-Twilio

-Maven

-Raspberry Pi

# What I've learned

-Maven

-SSH

-Bash

-Port forwarding

-Spark (Java web server)

-Pi4J (Raspberry Pi for Java)

-Webhook technology

---
> Project compiled with Maven.




