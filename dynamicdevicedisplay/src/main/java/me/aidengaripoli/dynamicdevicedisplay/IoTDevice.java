package me.aidengaripoli.dynamicdevicedisplay;

import java.io.InputStream;

public class IoTDevice {
    public String displayXml;
    public String deviceName;

    private InputStream displayStream;

    public IoTDevice(String fileName, String deviceName){
        displayXml = fileName;
        this.deviceName = deviceName;
    }

    public void setDisplayStream(InputStream displayStream) {
        this.displayStream = displayStream;
    }

    public InputStream getDisplayStream(){
        return displayStream;
    }
}
