@echo off
REM Run script for manually compiled version

echo ========================================
echo Running StudyConnect Application
echo ========================================
echo.

if not exist bin (
    echo bin folder not found! Please compile first using compile-manual.bat
    pause
    exit /b 1
)

java -cp "bin;lib/*" main.StudyConnectMain
