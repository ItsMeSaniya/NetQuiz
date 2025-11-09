#!/bin/bash
# Run script for manually compiled version

echo "========================================"
echo "Running StudyConnect Application"
echo "========================================"
echo

if [ ! -d "bin" ]; then
    echo "bin folder not found! Please compile first using ./compile-manual.sh"
    exit 1
fi

java -cp "bin:lib/*" main.StudyConnectMain
