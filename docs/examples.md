# Device Examples

## Kettle

```xml
<?xml version="1.0" encoding="UTF-8"?>
<device>
    <group id="1" permission="RR">
        <name>Kettle</name>
        <room>Kitchen</room>
    </group>
    <group id="2" permission="WR">
        <gui_element id="1">
            <type>switchtoggle</type>
            <disp_settings>On/Off</disp_settings>
            <status_location>2</status_location>
            <comment>Turn on or off</comment>
        </gui_element>
    </group>
    <group id="3" permission="WR">
        <gui_element id="1">
            <type>buttongroup</type>
            <disp_settings> ,Boil</disp_settings>
            <status_location>3</status_location>
            <comment>Start Boil</comment>
        </gui_element>
    </group>
    <group id="4" permission="WR">
        <gui_element id="1">
            <type>switchtoggle</type>
            <disp_settings>Keep Warm</disp_settings>
            <status_location>4</status_location>
            <comment>Keep warm</comment>
        </gui_element>
    </group>
    <group id="5" permission="WR">
        <gui_element id="1">
            <type>rangeinput</type>
            <disp_settings>Target Temperature,Set,10,150</disp_settings>
            <status_location>5</status_location>
            <comment>Set the target temperature</comment>
        </gui_element>
    </group>
    <group id="6" permission="WR">
        <gui_element id="1">
            <type>selection</type>
            <disp_settings>Temp Settings,Coffee,Hot Chocolate,Water</disp_settings>
            <status_location>6</status_location>
            <comment>Set temp settings</comment>
        </gui_element>
    </group>
</device>
```

## Security Camera

```xml
<?xml version="1.0" encoding="UTF-8"?>
<device>
    <group id="1" permission="RR">
        <name>Security Camera</name>
        <room>Outside</room>
    </group>
    <group id="1" permission="WR">
        <gui_element id="1">
            <type>switchtoggle</type>
            <disp_settings>On/Off</disp_settings>
            <status_location>1</status_location>
            <comment>Turn TV On or Off</comment>
        </gui_element>
        <gui_element id="2">
            <type>selection</type>
            <disp_settings>Source,Cam1,Cam2,Cam3,Cam4</disp_settings>
            <status_location>4</status_location>
            <comment>Camera source</comment>
        </gui_element>
    </group>
    <group id="2" permission="WR">
        <gui_element id="1">
            <type>buttontoggle</type>
            <disp_settings>Record,Recording,Not Recording</disp_settings>
            <status_location>5</status_location>
            <comment>Recording</comment>
        </gui_element>
    </group>
    <group id="3" permission="WR">
        <gui_element id="1">
            <type>directionalbuttons</type>
            <disp_settings>Camera Movement,↑,→,↓,←</disp_settings>
            <status_location>3</status_location>
            <comment>Directional Arrows</comment>
        </gui_element>
    </group>
    <group id="4" permission="WR">
        <gui_element id="1">
            <type>buttongroup</type>
            <disp_settings> ,Stream</disp_settings>
            <status_location>3</status_location>
            <comment>Stream camera</comment>
        </gui_element>
    </group>
</device>
```

## TV

```xml
<?xml version="1.0" encoding="UTF-8"?>
<device>
    <group id="0" permission="RR">
        <name>TV</name>
        <room>Living Room</room>
    </group>
    <group id="1" permission="WR" frame="false" orientation="horizontal">
        <gui_element id="1">
            <type>switchtoggle</type>
            <disp_settings>On/Off</disp_settings>
            <status_location>1</status_location>
            <comment>Turn TV On or Off</comment>
        </gui_element>
        <gui_element id="2">
            <type>selection</type>
            <disp_settings>Source,HDMI1,HDMI2,HDMI3,HDMI4,AV</disp_settings>
            <status_location>4</status_location>
            <comment>Input Source</comment>
        </gui_element>
    </group>
    <group id="2" permission="WR" frame="true" orientation="horizontal">
        <gui_element id="1">
            <type>buttontoggle</type>
            <disp_settings>Record,Recording,Not Recording</disp_settings>
            <status_location>5</status_location>
            <comment>Recording</comment>
        </gui_element>
        <gui_element id="2">
            <type>buttontoggle</type>
            <disp_settings>Mute,Muted,Unmuted</disp_settings>
            <status_location>6</status_location>
            <comment>Mute</comment>
        </gui_element>
    </group>
    <group id="3" permission="WR">
        <gui_element id="1">
            <type>stepper</type>
            <disp_settings>Channel,0,100</disp_settings>
            <status_location>2</status_location>
            <comment>Channel selection</comment>
        </gui_element>
    </group>
    <group id="4" permission="WR">
        <gui_element id="1">
            <type>stepper</type>
            <disp_settings>Volume,0,100</disp_settings>
            <status_location>2</status_location>
            <comment>Volume selection</comment>
        </gui_element>
    </group>
    <group id="5" permission="WR">
        <gui_element id="1">
            <type>directionalbuttons</type>
            <disp_settings>~,↑,→,↓,←</disp_settings>
            <status_location>3</status_location>
            <comment>Directional Arrows</comment>
        </gui_element>
    </group>
    <group id="6" permission="WR" frame="true" orientation="vertical">
        <gui_element id="1">
            <type>buttongroup</type>
            <disp_settings>~,1,2,3</disp_settings>
            <status_location>6</status_location>
            <comment>Numpad</comment>
        </gui_element>
        <gui_element id="2">
            <type>buttongroup</type>
            <disp_settings>~,4,5,6</disp_settings>
            <status_location>6</status_location>
            <comment>Numpad</comment>
        </gui_element>
        <gui_element id="3">
            <type>buttongroup</type>
            <disp_settings>~,7,8,9</disp_settings>
            <status_location>6</status_location>
            <comment>Numpad</comment>
        </gui_element>
        <gui_element id="4">
            <type>buttongroup</type>
            <disp_settings>~,Back,0,Info</disp_settings>
            <status_location>6</status_location>
            <comment>Numpad</comment>
        </gui_element>
    </group>
</device>
```

## Thermostat

```xml
<?xml version="1.0" encoding="UTF-8"?>
<device>
    <group id="1" permission="RR">
        <name>Thermostat</name>
        <room>Living Room</room>
    </group>
    <group id="2" permission="WR">
        <gui_element id="1">
            <type>status</type>
            <disp_settings>Current Temp:</disp_settings>
            <status_location>4</status_location>
            <comment>current temperature</comment>
        </gui_element>
    </group>
    <group id="3" permission="WR">
        <gui_element id="1">
            <type>status</type>
            <disp_settings>Target Temp:</disp_settings>
            <status_location>4</status_location>
            <comment>Target temperature</comment>
        </gui_element>
    </group>
    <group id="4" permission="WR">
        <gui_element id="1">
            <type>stepper</type>
            <disp_settings>Temperature,0,80</disp_settings>
            <status_location>4</status_location>
            <comment>Current Temperature</comment>
        </gui_element>
    </group>
    <group id="5" permission="WR">
        <gui_element id="1">
            <type>status</type>
            <disp_settings>Fan speed:</disp_settings>
            <status_location>4</status_location>
            <comment>Current Fan speed</comment>
        </gui_element>
    </group>
    <group id="6" permission="WR">
        <gui_element id="1">
            <type>plusminus</type>
            <disp_settings>Fan Speed,0,10</disp_settings>
            <status_location>4</status_location>
            <comment>Fan speed</comment>
        </gui_element>
    </group>
    <group id="7" permission="WR">
        <gui_element id="1">
            <type>scheduler</type>
            <disp_settings>Schedule an action,Submit,Heat,Cool,Shutdown,Startup</disp_settings>
            <status_location>4</status_location>
            <comment>Select and action and schedule a time</comment>
        </gui_element>
    </group>
</device>
```
