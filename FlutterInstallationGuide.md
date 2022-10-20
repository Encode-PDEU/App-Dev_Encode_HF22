# Installation Guide

## System Requirements

To install and run Flutter, your development environment must meet these minimum requirements:

**Operating Systems:**  
Windows 10 or later (64-bit), x86-64 based.

**Disk Space:**  
1.64 GB (does not include disk space for IDE tools).

**Tools:**  
Flutter depends on these tools being available in your environment.
- Windows PowerShell 5.0 or newer (this is pre-installed with Windows 10)
- Git for Windows 2.x, with the Use Git from the Windows Command Prompt option.


***


## Downloads
- [Git](https://git-scm.com/downloads)
- [Flutter SDK](https://storage.googleapis.com/flutter_infra_release/releases/stable/windows/flutter_windows_3.3.4-stable.zip)
- [Android Studio](https://developer.android.com/studio)

Optional
- [VSCode](https://code.visualstudio.com/download)
- [Visual Studio 2022](https://visualstudio.microsoft.com/downloads/)


***


## Installation Steps

**Git**

- Run the executable file probable with the name ```Git-2.38.0-64-bit.exe```.

- Simply do next next install with all the default options.

**Flutter SDK**

- Extract the ```flutter_windows_3.3.4-stable.zip``` file to your preferred location. It wont be easy to change the location later. So extract the zip accordingly.

- Once installed, we need to set the ```PATH environment variable```.

- To set the environment variable, navigate to the ```bin``` folder inside ```flutter``` folder and copy the full path. Example: D:/dev/flutter/bin

- Now go to the ```properties of This PC``` and navigate to
    ```    
    Advance System Settings >> Environment Variables
    ```

- There in the user variables or in the system variables edit the ```Path``` variable.

- To edit the ```Path``` variable you can either double click the path or select the path and click the edit button given below.

- In the edit prompt, click ```new``` and ```paste``` the path to bin folder, copied earlier.

**Checking Installation**

- Open command prompt and run the following command.
    ```
    flutter doctor
    ```

- Depending on your system and internet speed this may take a while.

- It may download ```dart sdk```.

- And after everything is finished, it should give the following output.
```
Running "flutter pub get" in flutter_tools... 8,9s
Doctor summary (to see all details, run flutter doctor -v):
[√] Flutter (Channel stable, 3.3.4, on Microsoft Windows [Version 10.0.22000.978], locale en-US)
[X] Android toolchain - develop for Android devices
    X Unable to locate Android SDK.
    Install Android Studio from: https://developer.android.com/studio/index.html
    On first launch it will assist you in installing the Android SDK components.
    (or visit https://flutter.dev/docs/get-started/install/windows#android-setup for detailed instructions).
    If the Android SDK has been installed to a custom location, please use
    'flutter config --android-sdk' to update to that location.

[√] Chrome - develop for the web
[X] Visual Studio - develop for Windows
     X Visual Studio not installed; this is necessary for Windows development.
   	Download at https://visualstudio.microsoft.com/downloads/.
   	Please install the "Desktop development with C++" workload, including all of its default components
[!] Android Studio (not installed)
[√] Connected device (2 available)
[√] HTTP Host Availability

! Doctor found issues in 3 categories.
```


**Android Studio**

- Run the ```android-studio-2021.3.1.16-windows.exe```.

- Click next next and keep the ```standard installation```.

- Click next next and finish.

- To accept the licences make sure you check all the licences on the left tab.

- Wait for the installaion, it will take time.

- If you do not plan to install VSCode then you need to install the ```flutter plugin``` in the marketplace. It is not required if you will be using vscode.
    
    - To install the plugins go to the plugins tab on the left side and search for flutter in the marketplace.
    
    - Click install. It will ask you to install the ```dart plugin```, install it as well.
    
    - If it asks you to restart the android studio, do that.

- Next, go to ```sdk manager``` in ```more actions```. There in the *SDK Tools* tab make sure that the ```Android SDK Command-line Tools (latest)``` is checked. Then hit ok.

- If it was already checked then it wont download anything, otherwise it will first download the command-line tools.

**Checking the installation**

- Reopen command prompt and run:
    ```
    flutter doctor
    ```

- this time it should give the following output.
```
Doctor summary (to see all details, run flutter doctor -v):
[√] Flutter (Channel stable, 3.3.4, on Microsoft Windows [version 10.0.22000.978), locale en-US)
[!] Android toolchain - develop for Android devices (Android SDK version 32.1.0-rc1)
    ! Some Android licenses not accepted. To resolve this, run: flutter doctor --android-licenses
[√] Chrome - develop for the web
[X] Visual Studio - develop for Windows
    X Visual Studio not installed; this is necessary for Windows development.
      Download at https://visualstudio.microsoft.com/downloads/.
      Please install the "Desktop development with C++" workload, including all of its default components
[√] Android Studio (version 2021.2)
[√] Connected device (2 available)
[√] HTTP Host Availability

! Doctor found issues in 2 categories.
```

**Accepting Android Licenses**

- Run the following command. And when asked, input y to all prompts, to accept licenses.
    ```
    flutter doctor --android-licenses
    ```

**Checking the installation**

- Re-running ```flutter doctor``` should give following output.
```
Doctor summary (to see all details, run flutter doctor -v):
[√] Flutter (Channel stable, 3.3.4, on Microsoft Windows [Version 10.0.22000.978], locale en-US)
[√] Android toolchain - develop for Android devices (Android SDK version 32.1.0-rc1)
[√] Chrome - develop for the web
[X] Visual Studio - develop for Windows
    X Visual Studio not installed; this is necessary for Windows development.
      Download at https://visualstudio.microsoft.com/downloads/.
      Please install the "Desktop development with C++" workload, including all of its default components
[√] Android Studio (version 2021.2)
[√] Connected device (2 available)
[√] HTTP Host Availability

! Doctor found issues in 1 category.
```

**VSCode (Optional but Recommended)**

- Run the installer (VSCodeUserSetup-{version}.exe). This will only take a minute.

- By default, VS Code is installed under C:\Users\{Username}\AppData\Local\Programs\Microsoft VS Code.

    - Alternatively, you can also download a ```Zip archive```, extract it and run Code from there.

- Open VSCode and go to the extensions tab in the left panel. Search for flutter, and install the verified ```Flutter extension``` provided by ```Dart Code```. 

- Here it will also ask you to install ```Dart extension```, install it as well.

**Visual Studio (Optional)**

- Run the ```VisualStudioSetup.exe```.

- Install the ```Desktop development with C++``` workload.

**Checking the installation**

- Re-run ```flutter doctor```. It should give the following output.

```
Doctor summary (to see all details, run flutter doctor -v):                                     
[√] Flutter (Channel stable, 3.3.4, on Microsoft Windows [Version 10.0.22000.978], locale en-IN)                        
[√] Android toolchain - develop for Android devices (Android SDK version 33.0.0)                                    
[√] Chrome - develop for the web                                   
[√] Visual Studio - develop for Windows (Visual Studio Community 2022 17.3.1)                                    
[√] Android Studio (version 2021.2)                                    
[√] VS Code (version 1.72.0)                                    
[√] Connected device (3 available)                          
[√] HTTP Host Availability                        
• No issues found! 
```

**If you are getting the above output, ```great job``` the installation is successful. Let me know if you are facing any problems**

### You can also checkout these youtube installation guide
- [Windows](https://youtu.be/BqHOtlh3Dd4)
- [MacOS](https://youtu.be/VPoqbBXzGtA)
- [Linux](https://youtu.be/cuKRW23TR2o)


***

## Creating a project

- Go to your preferred project directory.

- Open command prompt there.
    - type ```cmd``` into the folder path at the top of the window to open a command prompt within the folder.

- Run following command
    ```
    flutter create myapp
    ```

- Open ```myapp``` in VSCode or Android Studio.