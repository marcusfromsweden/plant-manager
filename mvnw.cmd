@echo off
rem Maven wrapper script for Windows

setlocal

set MAVEN_HOME=%~dp0\.mvn\wrapper
set MAVEN_OPTS=-Xmx1024m

if not exist "%MAVEN_HOME%\mvnw.cmd" (
    echo "Maven wrapper not found. Please run mvnw from a Unix-like environment."
    exit /b 1
)

"%MAVEN_HOME%\mvnw" %*