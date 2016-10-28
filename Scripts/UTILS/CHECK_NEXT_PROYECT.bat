@echo off
echo.
echo.
echo ****************************************
echo ****   UTILS CHECK NEXT JOB v1.0    ****
echo ****************************************

echo --------- Starting Check Next Job ------

cd C:/DevOps/DevOpsProyect/Scripts/UTILS
java CheckNextJob route.txt %1

echo --------------- Check OK ---------------
