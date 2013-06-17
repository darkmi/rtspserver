@echo off
echo [INFO] Generate source code from entity (according templates) to %cd%\generated dir.
echo [INFO] Make sure maven's ant task jar in ant's classpath.
call ant generate.code
echo [INFO] Artifacts will generate  in %cd%\generated dir.
pause