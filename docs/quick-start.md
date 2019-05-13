# Quick Start

## Installation

### Requirements

- Android 8.0+ (API 26+)
- Android Studio 3.0+

### Gradle

**Step 1**: Add the JitPack repository to your root/project `build.gradle`.

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io/' }
  }
}
```

**Step 2**: Add the dependency by including it in your module's `app/build.gradle`:

```
dependencies {
  ...
  implementation 'com.github.aidengaripoli:dynamic-device-display:v0.1.1-alpha'
}
```

**Step 3**: Add Java 1.8 compatibility. Add the following `compileOptions` to your `app/build.gradle`

```
android {
  ...
  compileOptions {
      targetCompatibility JavaVersion.VERSION_1_8
      sourceCompatibility JavaVersion.VERSION_1_8
  }
}
```

For more information visit the Android library [documentation](https://github.com/aidengaripoli/dynamic-device-display)

## Setup Main Activity

Your main Activity should contain the following:

```java
package your.package.name;

import android.os.Bundle;
import me.aidengaripoli.dynamicdevicedisplay.DeviceDiscoveryActivity;

public class MainActivity extends DeviceDiscoveryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
```

## Create A Device Definition

Next, generate the Android assets directory.

If the Projects Overview tool window is not visible, show it by **View > Tool Windows > Project**

Then, make sure the Project Overview is set to Android.

Now right click on your root project folder, and click **New > Folder > Assets Folder**

Create an xml file in the assets folder by right clicking the assets folder and click **New > File**

Call the file `exmaple.xml` and copy the following:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<device>
    <group id="1" permission="RR">
        <name>Example Device</name>
        <room>Room 1</room>
    </group>
    <group id="2" permission="WR">
        <gui_element id="1">
            <type>stepper</type>
            <disp_settings>Label,0,20</disp_settings>
            <status_location>1</status_location>
            <comment>This is a comment</comment>
        </gui_element>
    </group>
</device>
```

Now run the application.

