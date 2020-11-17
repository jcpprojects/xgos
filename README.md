# CHRISTMAS GIFTS ORDERING SYSTEM 


Initial draft to read a kafka stream of baskets and persistence in H2 db.

## Contents

* [Run locally](#run-locally)
* [Tests](#tests)
* [Build](#build)


## Run locally 

To run a local instance execute:

```sh
mvn spring-boot:run
```

Note that sever expects a kafka instance on localhost.

## Tests

To execute tests:

```sh
mvn clean test
```


## Build 

To build a self contained jar:

```sh
mvn clean install
```

The jar can then be executed with

```sh
java -jar target\xgos-0.0.1-SNAPSHOT.jar
```

Note that kafka installation is required.