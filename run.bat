@echo off
:: Se placer dans le dossier courant (files)
cd /d "%~dp0"

:: Compilation : on compile tous les fichiers .java dans le dossier actuel
javac --module-path "C:\\javafx-sdk\\lib" --add-modules javafx.controls,javafx.fxml *.java

:: Lancement : on lance la classe Principale qui est dans le paquet par défaut
java --module-path "C:\\javafx-sdk\\lib" --add-modules javafx.controls,javafx.fxml Principale

pause