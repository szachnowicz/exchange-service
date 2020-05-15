

# exchange-service
 Application allows exchange currency based on the data provided 
 from https://api.nbp.pl/api/exchangerates/tables/A/?format=json 
 
 Running script - required maven  5 in the main project folder   
 mvn clean install   ./targed -java -jar java -jar exchange-service-0.0.1-SNAPSHOT.jar  
 API documentation - http://localhost:8080/swagger-ui.html#/
 
embedded database http://localhost:8080/h2 passwords -> https://github.com/szachnowicz/exchange-service/blob/master/src/main/resources/application.yml 
