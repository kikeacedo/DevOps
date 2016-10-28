@echo off
echo.
echo.
echo ****************************************
echo ****     QA1 CHECK EXIT v1.0      ****
echo ****************************************

echo ----------- Starting Check Exit ----------

set /P EXIT_STATUS=<path.txt
echo %1
echo %EXIT_STATUS%
exit 1
echo ------------- Check Exit OK --------------
