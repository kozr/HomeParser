# Description:
This project extends the ability of smart homes by creating an almost real life SMS to RaspberryPi connection. In this sample repo, a LED is coded in to activate from Raspberry Pi's GPIO04 pin once the SMS message "LED on" is sent to a specific phone number, and could be turned off by texting "LED off". 

# How to use:
TBA

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




