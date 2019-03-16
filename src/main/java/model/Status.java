package model;

public enum Status {
    ON("ON"),
    OFF("OFF"),
    WARNING("WARNING");

    private String statusOfDevice;

    Status(String status) {
        this.statusOfDevice = status;
    }

    public String statusReport() {
        return statusOfDevice;
    }

}
