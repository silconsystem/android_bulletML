rem --------------------------------------------------------
rem Run this batch file to build. If you have problems 
rem with the path then reset the system vars ANT_HOME, JAVA_HOME
rem and modify this file to set the appropriate defaults.
rem --------------------------------------------------------

setlocal

if not "%ANT_HOME%" == "" goto gotAntHome

set ANT_HOME=C:\Program Files\java\ant\apache-ant-1.7.0

set PATH=%PATH%;%ANT_HOME%\bin

:gotAntHome


if not "%JAVA_HOME%" == "" goto gotJavaHome

set JAVA_HOME=C:\Program Files\Java\jdk\jdk1.6.0_12

set PATH=%PATH%;%JAVA_HOME%\bin

:gotJavaHome

call ant -f "build.xml" %1

endlocal
