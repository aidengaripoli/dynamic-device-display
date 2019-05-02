package me.aidengaripoli.dynamicdevicedisplay;

import java.util.ArrayList;
import java.util.List;

public class IotNetworkDiscovery {

    public List<IoTDevice> findDevices(){

        List<IoTDevice> devices = new ArrayList<>();

        devices.add(new IoTDevice("Kettle.xml", "Kettle"));
        devices.add(new IoTDevice("TV.xml", "TV"));
        devices.add(new IoTDevice("SecCamera.xml", "Security Camera"));
        devices.add(new IoTDevice("Thermostat.xml", "Thermostat"));
        devices.add(new IoTDevice("Elements.xml", "Elements"));

        return devices;
    }
}
