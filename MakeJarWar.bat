cd Deploy\Jar
jar -cvf ESaleJ.jar .
copy ESaleJ.jar ..\Ear
cd..\War
jar -cvf servlet.war .
copy servlet.war ..\Ear
cd..\..
