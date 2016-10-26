@echo off
echo.
echo.
echo ****************************************
echo ****     QA1 CHECK EXIT v1.0      ****
echo ****************************************

echo ----------- Starting Check Exit ----------

IF %EXIT_STATUS% == %1 THEN
	EXIT 0
ELSE
	EXIT 1
echo ------------- Check Exit OK --------------
