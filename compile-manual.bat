@echo off
REM Manual compilation script without Maven

echo ========================================
echo Manual Build for StudyConnect
echo ========================================
echo.

REM Create bin directory
if not exist bin mkdir bin

REM Check for lib directory
if not exist lib (
    echo Error: lib folder not found!
    echo.
    echo Please create a 'lib' folder and download:
    echo 1. flatlaf-3.2.5.jar
    echo 2. flatlaf-intellij-themes-3.2.5.jar
    echo.
    echo Download from: https://github.com/JFormDesigner/FlatLaf/releases
    pause
    exit /b 1
)

echo Compiling Java files...
javac -cp "lib/*" -d bin -sourcepath src src/main/StudyConnectMain.java src/main/model/*.java src/main/network/*.java src/main/ui/*.java src/main/util/*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo Compilation Successful!
    echo ========================================
    echo.
    echo To run the application:
    echo   java -cp "bin;lib/*" main.StudyConnectMain
    echo.
    echo Or use: run-manual.bat
) else (
    echo.
    echo Compilation failed! Please check the errors above.
)

pause
