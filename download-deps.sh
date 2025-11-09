#!/bin/bash
# Automatic dependency downloader for Linux/Mac

echo "========================================"
echo "StudyConnect Dependency Downloader"
echo "========================================"
echo

# Create lib folder if it doesn't exist
if [ ! -d "lib" ]; then
    echo "Creating lib folder..."
    mkdir lib
fi

echo "Downloading dependencies..."
echo

# Check if curl or wget is available
if command -v curl &> /dev/null; then
    echo "Using curl to download..."
    
    echo "Downloading flatlaf-3.2.5.jar..."
    curl -L -o lib/flatlaf-3.2.5.jar https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar
    
    echo "Downloading flatlaf-intellij-themes-3.2.5.jar..."
    curl -L -o lib/flatlaf-intellij-themes-3.2.5.jar https://repo1.maven.org/maven2/com/formdev/flatlaf-intellij-themes/3.2.5/flatlaf-intellij-themes-3.2.5.jar
    
elif command -v wget &> /dev/null; then
    echo "Using wget to download..."
    
    echo "Downloading flatlaf-3.2.5.jar..."
    wget -O lib/flatlaf-3.2.5.jar https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar
    
    echo "Downloading flatlaf-intellij-themes-3.2.5.jar..."
    wget -O lib/flatlaf-intellij-themes-3.2.5.jar https://repo1.maven.org/maven2/com/formdev/flatlaf-intellij-themes/3.2.5/flatlaf-intellij-themes-3.2.5.jar
    
else
    echo "Error: Neither curl nor wget found!"
    echo
    echo "Please download manually from:"
    echo "1. https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar"
    echo "2. https://repo1.maven.org/maven2/com/formdev/flatlaf-intellij-themes/3.2.5/flatlaf-intellij-themes-3.2.5.jar"
    echo
    echo "Place them in the lib folder."
    echo
    echo "Or use Maven: ./build.sh"
    exit 1
fi

echo
echo "========================================"
echo "Download Complete!"
echo "========================================"
echo
echo "Files downloaded to lib folder:"
ls -lh lib/
echo
echo "Next steps:"
echo "1. Run ./compile-manual.sh to build"
echo "2. Run ./run-manual.sh to start the app"
