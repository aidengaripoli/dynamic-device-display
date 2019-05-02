package me.aidengaripoli.dynamicdevicedisplay;

public class IoTDevice {
    public String displayXml;
    public String deviceName;

    IoTDevice(String fileName, String deviceName){
        displayXml = fileName;
        this.deviceName = deviceName;
    }
}
