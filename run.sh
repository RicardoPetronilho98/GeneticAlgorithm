# creating temporary folders to contain all .class files
mkdir tmp/ 
mkdir tmp/ga/

# compile all .java files on -> Application/Library sources
javac src/main/java/ga/*.java

# move all .class generated classes files to tmp folder 
mv src/main/java/ga/*.class tmp/ga/

# compile test class by importing classpath (-cp tmp/) where all (ga) package .class files are located
javac -cp tmp/ src/test/java/BinaryGeneticAlgorithmTeste.java

# run test class importing all classes from classpath (package ga + own test lass)
java -cp src/test/java/:tmp/ BinaryGeneticAlgorithmTeste

# delete temporary files
rm -r tmp/
rm src/test/java/*.class