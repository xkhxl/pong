@echo off

:: Navigate to the directory where this batch file is located
cd /d %~dp0

:: Go into src folder
cd src

:: Create bin folder in root if it doesn't exist
if not exist ..\bin (
    mkdir ..\bin
)

:: Compile from src to bin
javac -d ..\bin WelcomePage.java

:: Run using classpath from bin
java -cp ..\bin WelcomePage

pause
