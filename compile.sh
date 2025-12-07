#!/bin/bash
# Script untuk compile project
cd "$(dirname "$0")"
echo "Compiling Java files..."
javac -d bin src/*.java
echo "Compile selesai! File .class tersimpan di folder bin/"
