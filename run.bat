@echo off
set JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot"
set PATH=%JAVA_HOME%\bin;%PATH%
set JAVAFX_PATH=C:\openjfx-21.0.11_windows-x64_bin-sdk\javafx-sdk-21.0.11\lib

echo Compilation en cours...
javac --module-path %JAVAFX_PATH% --add-modules javafx.controls,javafx.fxml *.java

echo Lancement en cours...
java --module-path %JAVAFX_PATH% --add-modules javafx.controls,javafx.fxml Principale

pause