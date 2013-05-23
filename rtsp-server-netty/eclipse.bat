@echo off
echo [INFO] Use maven eclipse plugin download jar and generate eclipse project files.
echo [INFO] Please add "-Declipse.workspace=<path-to-eclipse-workspace>" at end of follow command.

cd %~dp0
call mvn eclipse:clean eclipse:eclipse

pause