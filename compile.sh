mvn assembly:assembly package
java -verbose:gc -cp ~/Minnehack2017-Server/target/Minnehack2017-Server-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.nebby.server.Server
