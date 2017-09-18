cd deploy\war
md WEB-INF
cd WEB-INF
md classes
cd..\..\..
del Deploy\Jar\*.class
del Deploy\Jar\META-INF\*.xml
del Deploy\Jar\*.jar
del Deploy\War\WEB-INF\classes\*.class
del Deploy\War\WEB-INF\*.xml
del Deploy\War\*.war
del Deploy\Ear\*.jar
del Deploy\Ear\*.war
del Deploy\Ear\*.ear
del Deploy\Ear\META-INF\*.xml
javac -d .\Deploy\Jar Code\Auction\*.java
javac -d .\Deploy\Jar Code\Registration\*.java
javac -d .\Deploy\Jar Code\Search\*.java
javac -d .\Deploy\Jar Code\Sell\*.java
javac -d .\Deploy\War\WEB-INF\classes Code\Servlets\*.java
