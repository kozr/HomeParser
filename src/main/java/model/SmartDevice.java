package model;

import java.util.Calendar;
import java.util.Objects;

import static ui.Main.myLed;


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

    public void updateDevice() {
        if (getStatus() == Status.ON) {
            if (deviceName.equals("led")) {
                myLed.high();
            }
        } else if (getStatus() == Status.OFF) {
            if (deviceName.equals("led")) {
                myLed.low();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDevice that = (SmartDevice) o;
        return Objects.equals(deviceName, that.deviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceName);
    }
}
