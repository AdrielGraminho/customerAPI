Requirements:

Java version 17

Maven

Docker

Docker Compose

##
To run postgresql:

docker compose up -d

To run application: 

mvn clean package install

cd ./target 

java -jar customer-0.0.1-SNAPSHOT.jar

Endpoints Usage Examples:

Find All Customers:
curl --request GET \
--url http://localhost:8080/customer

Find Customer By ID:
curl --request GET \
--url http://localhost:8080/customer/4

Save Customer:
curl --request POST \
--url http://localhost:8080/customer \
--header 'Content-Type: application/json' \
--data '{
"name": "adriel",
"email": "adrielgraminho@gmail.com",
"cpf": "826377364",
"dateOfBirth": "1998-02-28"
}'

Edit Customer:
curl --request PUT \
--url http://localhost:8080/customer \
--header 'Content-Type: application/json' \
--data '{
"idCustomer": 3,
"name": "adriel4",
"email": "adrielgraminho@gmail.com",
"cpf": "826377364",
"dateOfBirth": "1998-02-28"
}'

Delete Customer:
curl --request PUT \
--url 'http://localhost:8080/customer/delete?idCustomer=4'

To run tests:
mvn clean test
