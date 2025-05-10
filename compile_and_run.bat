@echo off
echo Compiling Java files...
javac src/*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo Running program...
    java -cp src Main
) else (
    echo Compilation failed!
)

@REM pause 