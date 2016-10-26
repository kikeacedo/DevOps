@echo off
echo.
echo.
echo ****************************************
echo ****     QA1 SVN CHECKOUT v1.0      ****
echo ****************************************

echo ----------- Starting Checkout ----------

set /P DN_PATH=<path.txt
echo %DN_PATH%
set NEXT_JOB=QA1_LOCK_PROYECTS
echo ------------- Checkout OK --------------
