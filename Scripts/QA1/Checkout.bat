@echo off
echo.
echo.
echo ****************************************
echo ****  QA1 DN Checkout Utility v1.0  ****
echo ****************************************

rem DN_Path
set LogPath=C:\ongoing\scripts\DevOps\QA1\DN_Path.txt
set release=%DN_Path:~69%

echo.
rem https://172.18.108.116/svn/Orange/CIMA/branches/F2HA/1008_Deliveries/TEIDE_OSP_1008_107

echo %DN_Path% >%LogPath%

echo.
echo DN_Name: %release: =%

@exit 9
