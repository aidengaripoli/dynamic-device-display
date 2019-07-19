package me.aidengaripoli.dynamicdevicedisplay;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IotNetworkDiscovery {
    private static final String TAG = "IotNetworkDiscovery";
    private List<IoTDevice> devices;

    public IotNetworkDiscovery(AssetManager assets){
        devices = new ArrayList<>();
        try {
            String [] list = assets.list("");
            XmlParser xmlParser = new XmlParser();
            for (String file : list) {
                if(file.contains(".xml")){
                    devices.add(new IoTDevice(file, xmlParser.getDeviceName(assets.open(file))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

     static ArrayList<String> getDeviceInformation(IoTDevice device){
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
                commands.add("7-1,1,5:23 PM");
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
