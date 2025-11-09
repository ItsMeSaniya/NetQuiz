@echo off
REM Automatic dependency downloader for Windows

echo ========================================
echo StudyConnect Dependency Downloader
echo ========================================
echo.

REM Check if lib folder exists
if not exist lib (
    echo Creating lib folder...
    mkdir lib
)

echo Downloading dependencies...
echo.

REM Check if curl or wget is available
where curl >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo Using curl to download...
    
    echo Downloading flatlaf-3.2.5.jar...
    curl -L -o lib\flatlaf-3.2.5.jar https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar
    
    echo Downloading flatlaf-intellij-themes-3.2.5.jar...
    curl -L -o lib\flatlaf-intellij-themes-3.2.5.jar https://repo1.maven.org/maven2/com/formdev/flatlaf-intellij-themes/3.2.5/flatlaf-intellij-themes-3.2.5.jar
    
    echo.
    echo ========================================
    echo Download Complete!
    echo ========================================
    echo.
    echo Files downloaded to lib folder:
    dir lib
    echo.
    echo Next steps:
    echo 1. Run compile-manual.bat to build
    echo 2. Run run-manual.bat to start the app
    
) else (
    echo Error: curl not found!
    echo.
    echo Please download manually from:
    echo 1. https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar
    echo 2. https://repo1.maven.org/maven2/com/formdev/flatlaf-intellij-themes/3.2.5/flatlaf-intellij-themes-3.2.5.jar
    echo.
    echo Place them in the lib folder.
    echo.
    echo Or use Maven: build.bat
)

pause
