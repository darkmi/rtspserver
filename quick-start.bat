@echo off
echo [INFO] 确保默认JDK版本为JDK6.0及以上版本,已配置JAVA_HOME.

echo [INFO] 如不能连接Maven官方网站, 修改本文件去掉下面一行的注释.
rem set OFF_LINE=-o

set MVN=mvn
set ANT=ant
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=512m

if exist "tools\maven\apache-maven-2.2.1\" set MVN="%cd%\tools\maven\apache-maven-2.2.1\bin\mvn.bat"
if exist "tools\ant\apache-ant-1.8.1\" set ANT="%cd%\tools\ant\apache-ant-1.8.1\bin\ant.bat"
echo Maven命令为%MVN%
echo Ant命令为%ANT%

echo [Step 2] 安装VODLite3.0 所有modules项目到本地Maven仓库, 生成Eclipse项目文件.
call %MVN% %OFF_LINE% clean install -Dmaven.test.skip=true
call %MVN% %OFF_LINE% eclipse:clean eclipse:eclipse

:end
pause