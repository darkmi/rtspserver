@echo off
echo [INFO] 确保已用mvn install安裝tools\maven\archetype中的mini-web項目模板

set MVN=mvn
if exist "tools\maven\apache-maven-2.2.1\" set MVN="%cd%\tools\maven\apache-maven-2.2.1\bin\mvn.bat"
echo Maven命令为%MVN%

mkdir projects
cd projects
call mvn archetype:generate -DarchetypeCatalog=local

pause


