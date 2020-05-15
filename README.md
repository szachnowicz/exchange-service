# exchange-service
Application allows exchanage currecy based on the data provided form https://api.nbp.pl/api/exchangerates/tables/A/?format=json

Running script - required maven 
in main project folder 
mvn clean install 
./targed -java -jar java -jar exchange-service-0.0.1-SNAPSHOT.jar 


API documentation - http://localhost:8080/swagger-ui.html#/

emmbeded database http://localhost:8080/h2 passowrds -> https://github.com/szachnowicz/exchange-service/blob/master/src/main/resources/application.yml

