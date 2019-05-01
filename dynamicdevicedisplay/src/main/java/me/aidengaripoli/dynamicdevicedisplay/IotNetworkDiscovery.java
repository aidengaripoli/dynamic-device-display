package me.aidengaripoli.dynamicdevicedisplay;

import java.util.HashMap;
import java.util.Map;

public class IotNetworkDiscovery {

    public Map<String,String> findDevices(){
        Map<String,String> devices = new HashMap<>();

        devices.put("Kettle.xml", "Kettle");
        devices.put("TV.xml", "TV");
        devices.put("SecCamera.xml", "Security Camera");
        devices.put("Thermostat.xml", "Thermostat");
        devices.put("Elements.xml", "Elements");

        return devices;
    }
}
