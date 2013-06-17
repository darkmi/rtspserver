@echo off
echo [INFO] Use maven jetty-plugin run the project, with debug options.

cd %~dp0
cd ..

set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m  -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
call mvn jetty:run -Dmaven.test.skip=true

cd bin
pause
