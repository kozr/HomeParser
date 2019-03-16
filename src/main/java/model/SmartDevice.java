package model;

import java.util.Calendar;

public class SmartDevice {
    private String deviceName;
    private Status parsedStatus;
    private Calendar offTime;

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
        parsedStatus = Status.OFF;
        offTime = null;
    }

    public void setParsedStatus(Status parsedStatus) {
        this.parsedStatus = parsedStatus;
    }

    public void setOffTime(Calendar offTime) {
        this.offTime = offTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public Status getStatus() {
        return parsedStatus;
    }

    public Calendar getOffTime() {
        return offTime;
    }

}
