@echo off
echo [INFO] Create schema by sql, and import default data from src/main/resources/data/default-data.xml by dbunit.

cd %~dp0
call ant init-db
pause