package model;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import java.util.Calendar;

import static ui.Main.gpio;

public class SmartDevice {
    private String deviceName;
    private Status parsedStatus;
    private Calendar offTime;
    private GpioPinDigitalOutput myLed;

    public SmartDevice(String deviceName) {
        myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
                "My LED",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
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

    public void updateDevice() {
        if (getStatus() == Status.ON) {
            myLed.high();
        } else if (getStatus() == Status.OFF) {
            myLed.low();
        }
    }
}
