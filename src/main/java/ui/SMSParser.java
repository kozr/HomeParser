package ui;

import exceptions.InvalidInputException;
import exceptions.NonExistenceDeviceException;
import exceptions.StatusUnclearException;
import model.SmartDevice;
import model.Status;

import java.util.HashSet;
import java.util.Set;

public class SMSParser {

    private static String[] listTerms;
    private static SmartDevice smartDeviceRecognized;
    public static Set<SmartDevice> allSmartDevices = new HashSet();

    public static void parse(String message) {
        listTerms = message.toLowerCase().split(" ");
        if (listTerms.length > 1) {
            smartDeviceRecognized = returnSmartDevice(listTerms[0]);
            parsedStatus();
            if (smartDeviceRecognized != null) {
                smartDeviceRecognized.updateDevice();
            }
        } else {
            throw new InvalidInputException("Invalid input, please send an SMS in the format of [device name] [update]");
        }
    }
    // Changes status of smart device based on second term
    private static void parsedStatus() {
        if (smartDeviceRecognized != null || listTerms[1].equals("add")) {
            if (listTerms[1].equals("on")) {
                smartDeviceRecognized.setParsedStatus(Status.ON);
            } else if (listTerms[1].equals("off")) {
                smartDeviceRecognized.setParsedStatus(Status.OFF);
            } else if (listTerms[1].equals("add")) {
                allSmartDevices.add(new SmartDevice(listTerms[0]));
            } else if (listTerms[1].equals("remove")) {
                allSmartDevices.remove(smartDeviceRecognized);
            } else {
                throw new StatusUnclearException("Sorry, I do not comprehend the update given.");
            }
        } else {
            throw new NonExistenceDeviceException("Sorry, I do not recognize the device.");
        }
    }

    public static SmartDevice returnSmartDevice(String given) {
        for (SmartDevice sd : allSmartDevices) {
            if (sd.getDeviceName().equals(given)) {
                return sd;
            }
        }
        return null;
    }

    public static String getTermOne() {
        return listTerms[0];
    }

    public static String getTermTwo() {
        return listTerms[1];
    }
}
