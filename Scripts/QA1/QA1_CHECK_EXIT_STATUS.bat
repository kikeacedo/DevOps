@echo off
echo.
echo.
echo ****************************************
echo ****     QA1 CHECK EXIT v1.0      ****
echo ****************************************

echo ----------- Starting Check Exit ----------

IF %EXIT_STATUS% == %1(
	EXIT 0
	echo IGUAL
)ELSE(
	EXIT 1
	echo NO IGUAL
)
echo ------------- Check Exit OK --------------
