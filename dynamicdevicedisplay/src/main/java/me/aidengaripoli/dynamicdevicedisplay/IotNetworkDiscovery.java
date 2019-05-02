package me.aidengaripoli.dynamicdevicedisplay;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class IotNetworkDiscovery {
    private static final String TAG = "IotNetworkDiscovery";
    private List<IoTDevice> devices;

    public IotNetworkDiscovery(){
        devices = new ArrayList<>();
        devices.add(new IoTDevice("Kettle.xml", "Kettle"));
        devices.add(new IoTDevice("TV.xml", "TV"));
        devices.add(new IoTDevice("SecCamera.xml", "Security Camera"));
        devices.add(new IoTDevice("Thermostat.xml", "Thermostat"));
        devices.add(new IoTDevice("Elements.xml", "Elements"));
    }

    public List<IoTDevice> findDevices(){
        //TODO: Discovery protocol and return a list of devices
        return devices;
    }

    public IoTDevice connectToDevice(String deviceName){
        //TODO: Find the device on the network and get its xml file.
        for (IoTDevice device: devices) {
            if(device.deviceName.equals(deviceName)){
                return device;
            }
        }

        Log.e(TAG, "connectToDevice: No device found with name:" + deviceName);
        return null;
    }

    public ArrayList<String> getDeviceInformation(IoTDevice device){
        // TODO: Retrieve the current status of each widget in the devices xml.
        ArrayList<String> commands = new ArrayList<>();
        switch (device.deviceName){
            case "Kettle":
                commands.add("5-1,100");
                commands.add("6-1,2");
                break;
            case "TV":
                commands.add("3-1,5");
                commands.add("4-1,7");
                commands.add("2-1,false");
                commands.add("2-2,false");
                commands.add("1-1,true");
                commands.add("1-2,3");
                break;
            case "Security Camera":
                commands.add("1-1,true");
                commands.add("1-2,2");
                break;
            case "Thermostat":
                commands.add("2-1,15");
                commands.add("3-1,18");
                commands.add("5-1,5");
                commands.add("7-1,1,4:20 PM");
                break;
            case "Elements":
                commands.add("1-1,true");
                commands.add("2-1,false");
                commands.add("3-1,9");
                commands.add("4-1,1258");
                commands.add("6-1,1");
                commands.add("7-1,12");
                commands.add("8-1,Hello");
                commands.add("9-1,20");
                commands.add("11-1,2,3:30 PM");
                break;
        }
        return commands;
    }
}
