# Elements

## Switch Toggle

#### XML Type

`switchtoggle`

#### Display Settings

`label`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>switchtoggle</type>
        <disp_settings>On/Off</disp_settings>
        <status_location>7</status_location>
        <comment>Light Switch</comment>
    </gui_element>
</group>
```

## Button Toggle

#### XML Type

`buttontoggle`

#### Display Settings

`label,positiveLabel,negativeLabel`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>buttontoggle</type>
        <disp_settings>Open/Close,Open,Close</disp_settings>
        <status_location>5</status_location>
        <comment>Light Switch</comment>
    </gui_element>
</group>
```

## Stepper

#### XML Type

`stepper`

#### Display Settings

`label,minValue,maxValue`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>stepper</type>
        <disp_settings>Number,0,10</disp_settings>
        <status_location>1</status_location>
        <comment>Number Counter</comment>
    </gui_element>
</group>
```

## Range Input

#### XML Type

`rangeinput`

#### Display Settings

`label,buttonLabel,minValue,maxValue`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>rangeinput</type>
        <disp_settings>Volume,Set,1000,3000</disp_settings>
        <status_location>1</status_location>
        <comment>Volume Slider</comment>
    </gui_element>
</group>
```

## Directional Buttons

#### XML Type

`directionalbuttons`

#### Display Settings

`label,topLabel,rightLabel,bottomLabel,leftLabel,centreLabel`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>directionalbuttons</type>
        <disp_settings>Direction,N,E,S,W</disp_settings>
        <status_location>1</status_location>
        <comment>Directional movement</comment>
    </gui_element>
</group>
```

## Selection

#### XML Type

`selection`

#### Display Settings

`label,option1,option2,option3,...,optionN`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>selection</type>
        <disp_settings>Move Door,one,two,three,four,five,six</disp_settings>
        <status_location>1</status_location>
        <comment>Door positions</comment>
    </gui_element>
</group>
```

## Status

#### XML Type

`status`

#### Display Settings

`label`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>status</type>
        <disp_settings>Voltage</disp_settings>
        <status_location>1</status_location>
        <comment>Voltage level</comment>
    </gui_element>
</group>
```

## Text Input

#### XML Type

`textinput`

#### Display Settings

`label,buttonLabel`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>textinput</type>
        <disp_settings>Name,Submit</disp_settings>
        <status_location>1</status_location>
        <comment>Name</comment>
    </gui_element>
</group>
```

## Progress

#### XML Type

`progress`

#### Display Settings

`label`

#### Example

```xml
<group id="1" permission="WR">
    <gui_element id="1">
        <type>progress</type>
        <disp_settings>Loading...</disp_settings>
        <status_location>1</status_location>
        <comment>Loading progression</comment>
    </gui_element>
</group>
```

## Button Group

#### XML Type

`buttongroup`

#### Display Settings

`label,buttonLabel1,buttonLabel2,...,buttonLabelN`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>buttongroup</type>
        <disp_settings>Numpad,1,2,3,4</disp_settings>
        <status_location>1</status_location>
        <comment>Numpad</comment>
    </gui_element>
</group>
```

## Schedular

#### XML Type

`scheduler`

#### Display Settings

`label,submitButtonLabel,option1,option2,...,optionN`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>scheduler</type>
        <disp_settings>Schedule an event,Submit,Mon,Tue,Wed,Thu,Fri</disp_settings>
        <status_location>1</status_location>
        <comment>Event scheduler</comment>
    </gui_element>
</group>
```

## Password

#### XML Type

`password`

#### Display Settings

`label,buttonLabel`

#### Example

```xml
<group id="1" permisison="WR">
    <gui_element id="1">
        <type>password</type>
        <disp_settings>Password,Submit</disp_settings>
        <status_location>1</status_location>
        <comment>Enter your password</comment>
    </gui_element>
</group>
```
