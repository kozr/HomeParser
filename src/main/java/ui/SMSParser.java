package parser;

import exceptions.InvalidInputException;
import exceptions.NonExistenceDeviceException;
import exceptions.StatusUnclearException;
import model.SmartDevice;
import model.Status;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class SMSParser {

    private Calendar offTime;
    private String[] listTerms;
    private SmartDevice smartDeviceRecognized;
    private static Set<SmartDevice> allSmartDevices = new HashSet();

    public void parse(String message) {
        listTerms = message.split(" ");
        if (listTerms.length > 1) {
            smartDeviceRecognized = returnSmartDevice(listTerms[0]);
            parsedStatus();
        } else {
            throw new InvalidInputException("Invalid input, please send an SMS in the format of [device name] [update]");
        }
    }

    private void parsedStatus() {
        if (smartDeviceRecognized != null || listTerms[1].equals("add")) {
            if (listTerms[1].toLowerCase().equals("on")) {
                smartDeviceRecognized.setParsedStatus(Status.ON);
            } else if (listTerms[1].toLowerCase().equals("off")) {
                smartDeviceRecognized.setParsedStatus(Status.OFF);
            } else if (listTerms[1].toLowerCase().equals("warning")) {
                smartDeviceRecognized.setParsedStatus(Status.WARNING);
            } else if (listTerms[1].toLowerCase().equals("add")) {
                allSmartDevices.add(new SmartDevice(listTerms[0]));
            } else if (listTerms[1].toLowerCase().equals("remove")) {
                allSmartDevices.remove(returnSmartDevice(listTerms[0]));
            } else {
                throw new StatusUnclearException("Sorry, I do not comprehend the update given.");
            }
        } else {
            throw new NonExistenceDeviceException("Sorry, I do not recognize the device.");
        }
    }


    public SmartDevice returnSmartDevice(String given) {
        for (SmartDevice sd : allSmartDevices) {
            if (sd.getDeviceName().toLowerCase().equals(given.toLowerCase())) {
                return sd;
            }
        }
        return null;
    }



}
