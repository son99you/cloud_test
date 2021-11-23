echo off
echo ----------------------------------------
echo              UbiService.bat
echo ----------------------------------------
echo.
set JAVA_DIR=D:\kto\jdk1.8.0_211
set UBISERVICE_DIR=D:\kto\workspace\eGs_epro_kto_dev\src\main\webapp\UbiService
set PROPERTY_DIR=%UBISERVICE_DIR%
set FONT_DIR=%UBISERVICE_DIR%\fonts\
set XMS=512M
set XMX=1024M
set CLASSPATH=%UBISERVICE_DIR%\lib\UbiServer.jar

echo.
echo [Directory Information]
echo - Java : %JAVA_DIR%
echo - UbiService : %UBISERVICE_DIR%
echo.

echo [Java Information]
%JAVA_DIR%\bin\java -version
echo.

echo [UbiService Start]
%JAVA_DIR%\bin\java -Xms%XMS% -Xmx%XMX% -Dfile.encoding=UTF-8 -Djava.awt.headless=true -Dsun.java2d.fontpath=%FONT_DIR% -classpath %CLASSPATH%;. com.ubireport.service.UbiService %PROPERTY_DIR%
pause>nul
