# Mobile UI e2e tests
Appium Demo

<h2> To run UI tests locally you need to: </h2>



<h3> (1) Install Java and set JAVA_HOME </h3>

1. Run in Terminal <code>java -version</code> to check if Java is already installed
2. If not, install Java SDK (11.0.19) https://www.oracle.com/java/technologies/downloads/#java11
3. Run <code>echo $SHELL</code> to see which shell you have: bash or zsh
4. Run <code>open ~/.zshrc</code> or <code>open ~/.bash_profile</code> if it already exists. If not create a profile.
5. Run <code>/usr/libexec/java_home -V | grep jdk</code>. You will get something like that in the output:  
<br><code>11.0.10 (x86_64) "Oracle Corporation" - "Java SE 11.0.10" /Library/Java/JavaVirtualMachines/jdk-11.0.10.jdk/Contents/Home</code>
6. Add JAVA_HOME and PATH to the profile as follows: 
<br><code>export JAVA_HOME="path_to_java_home"</code>
<br><code>export PATH=$JAVA_HOME/bin:$PATH</code>
<br>where 'path_to_java_home' is the output of <code>/usr/libexec/java_home</code> command
7. Execute <code>echo $JAVA_HOME</code> to check if the changes were applied



<h3> (2) Install Android Studio and set ANDROID_HOME </h3>

1. Install Android Studio
2. Add ANDROID_HOME and PATH variables to the profile as follows:
<br><code> export ANDROID_HOME=/Users/user_name/Library/Android/sdk</code>
<br><code> export PATH=${PATH}:$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools/bin</code>
<br>where 'user_name' is your user name
3. Execute <code>echo $ANDROID_HOME</code> to check if the changes were applied
4. Execute <code>adb</code> command to check if the changes were applied



<h3> (3) Install Node.js, Appium Server and required drivers</h3>

1. Install Node.js by running <code>brew install node</code>
2. Check if Node was installed: <code>node -v</code>
3. Check if NPM was installed: <code>npm -v</code>
4. Install server Appium 2.0 by running <code>npm i -g appium@next</code> command
5. Install UiAutomator2 driver for Android by running <code>appium driver install uiautomator2</code>
6. Install XCUITest driver for iOS by running <code>appium driver install xcuitest</code>
7. Run <code>appium driver list</code> to make sure the drivers have been installed
8. Install appium-doctor by running <code>npm install appium-doctor -g</code> command
9. Run <code>appium-doctor --android</code> for Android and <code>appium-doctor --iOS</code> for iOS to check if all the necessary dependencies for Android are installed. There should be no critical errors.



<h3> (4) Install -images plugin for visual testing</h3>

1. Run <code>appium plugin install images</code>


<h2> Android tests </h2>
<h3> Set up emulator for Android </h3>

1. Run <code>uname -m</code> to see the architecture of your host machine
2. Create Android emulator device as in android.xml file
3. During 'Select a system image' step choose an image which corresponds to architecture of your machine: use x86_64 image for a Mac with an Intel processor; use arm if it's a Mac with M1.

<h2> iOS tests </h2>
<h3> Change Simulator properties for iOS </h3>

1. Install Xcode
2. Run <code>xcrun xctrace list devices</code>
3. Adjust device parameters (platformVersion, deviceName, udid) in the xml file to match those on the local machine


<h2> Running tests </h2>
<h3> (1) Run Appium Server </h3>
Run Appium server: <code>appium --address 0.0.0.0 --port 4723 --relaxed-security --base-path /wd/hub</code>


<h3> (2) Run UI tests </h3>

<h4> FROM IntelliJ IDEA </h4>

1. Open the project in IntelliJ IDEA
2. Open Run -> Edit configurations
3. Add the following to the TestNG Environment variables <code>PLATFORM={Platform}</code> where Platform is either Android or iOS
4. Run Android tests by running corresponding xml file: android.xml or ios.xml

<h4> OR USING GRADLE </h4>

1. Install Gradle:
<br> <code>brew install gradle</code>
2. Navigate to the project directory in Terminal
3. Execute command <code>export PLATFORM={Platform}; gradle testAndroid</code>
4. After build is complete (successful or failed) run <code>gradle clean</code>

------------------------------

<h3> Troubleshooting common issues:</h3> 
- If you get an error because local port is busy, execute <code>npx kill-port 4723 10000</code>
<h3> Documentation:</h3> 
- https://github.com/appium/appium
- https://appium.github.io/appium/docs/en/2.0/quickstart/install/
- https://appium.io/docs/en/about-appium/getting-started/?lang=en
