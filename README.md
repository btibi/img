# Tech Challenge

This is a tech challenge for the IMG Arena. See more details here: [IMG Arena Tech Challenge](https://hackmd.io/LAt6HwlQTH-RLJS4478Gqg?view)
This is a tech challenge for the IMG Arena. 


# Run the application

    mvnw spring-boot:run

# Default data

Default data set is availabele after you start the app (see: *resources/data.sq*l). 
Check the [H2 Database Consol](http://localhost:8080/h2-console/login.jsp)

## Customers
|customerId|name |
|----------|-----|
|1         |Jon  |
|2         |Paul |
|3         |Tibor|

## Tournamets
|tournamentId|name     |
|------------|---------|
|1           |Wimbledon|
|2           |US Open  |

## Matches
|matchId|tournament|startDate       |playerA              |playerB            |
|-------|----------|----------------|---------------------|-------------------|
|1      |Wimbledon |2020-08-04 10:00|M Berrettini         |A Bedene           |
|2      |Wimbledon |2020-08-04 16:00|B Schnur             |M Baghdatis        |  
|3      |Wimbledon |2020-08-05 14:00|D Köpfer             |F Krajinović       |  
|4      |Wimbledon |2020-08-05 16:00|M Ebden              |D Schwartzman      |
|5      |Wimbledon |2020-08-06 13:00|L Pouille   		  |R Gasquet          |
|6      |Wimbledon |2020-08-06 14:00|A Bublik			  |G Barrère          |
|7      |Wimbledon |2020-08-07 15:00|J Clarke   		  |N Rubin            |  
|8      |Wimbledon |2020-08-08 16:00|L Harris             |R Federer          |
|9      |US Open   |2020-08-31 10:00|Kyle Edmund          |Pablo Andújar      |
|10     |US Open   |2020-08-31 16:00|Taylor Fritz         |Feliciano López    |
|11     |US Open   |2020-09-01 10:00|Guido Pella          |Pablo Carreño Busta|
|12     |US Open   |2020-09-01 16:00|Félix Auger-Aliassime|Denis Shapovalov   |
|13     |US Open   |2020-09-02 08:00|Fabio Fognini        |Reilly Opelka      |
|14     |US Open   |2020-09-02 14:00|Roberto Bautista Agut|Mikhail Kukushkin  |
|15     |US Open   |2020-09-03 08:00|Karen Khachanov      |Vasek Pospisil     |
|16     |US Open   |2020-09-03 16:00|Stefanos Tsitsipas   |Andrey Rublev      |

## Licenses
|licenseId|customer|tournament|match                              |type      |
|--------|---------|----------|-----------------------------------|----------|
|1       |Jon      |Wimbledon |                                   |tournament|
|2       |Jon      |          |Kyle Edmund vs Pablo Andújar       |match     |
|3       |Paul     |US Open   |                                   |tournament|
|4       |Paul     |          |M Ebden vs D Schwartzman           |match     |
|5       |Tibor    |          |M Berrettini vs A Bedene           |match     |
|6       |Tibor    |          |B Schnur vs M Baghdatis            |match     |
|7       |Tibor    |          |L Harris vs R Federer              |match     |
|8       |Tibor    |          |Stefanos Tsitsipas vs Andrey Rublev|match     |


# Endpoint
Check the  [API documentation](http://localhost:8080/swagger-ui.html)

## GET [​/api​/matches](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/matches-api/licensedMatchesByCustomerIds)

#### Curl
    curl -X GET "http://localhost:8080/api/matches?customerIds=1,2&summaryType=AvB" -H "accept: application/json"

#### Request URL
    http://localhost:8080/api/matches?customerIds=1,2&summaryType=AvB

## GET [​/api​/matches​/{customerId}](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/matches-api/licensedMatchesByCustomerId)

#### Curl

    curl -X GET "http://localhost:8080/api/matches/1?summaryType=AvBTime" -H "accept: application/json"

#### Request URL

    http://localhost:8080/api/matches/1?summaryType=AvBTime

