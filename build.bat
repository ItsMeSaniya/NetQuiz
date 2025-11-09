@echo off
REM Build script for StudyConnect (Windows)

echo ========================================
echo Building StudyConnect Application
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo Maven not found! Please install Maven or compile manually.
    echo.
    echo Manual compilation:
    echo 1. Download FlatLaf JAR files from: https://github.com/JFormDesigner/FlatLaf/releases
    echo 2. Place them in the lib folder
    echo 3. Run: compile-manual.bat
    pause
    exit /b 1
)

echo Building with Maven...
call mvn clean package

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo Build Successful!
    echo ========================================
    echo.
    echo To run the application:
    echo   java -cp "target\StudyConnect-1.0.0.jar;target\lib\*" main.StudyConnectMain
    echo.
    echo Or use: run.bat
) else (
    echo.
    echo Build failed! Please check the errors above.
)

pause
