## PoC WebSocket Spring boot and React.js

### Prerequisites

**Backend**

- Java Version 17 (Can use SDKMAN for Java Management - [Website](https://sdkman.io/))
- Maven

**Frontend**

- Node Version 20

### Development

**How to test the backend project**

Run Spring Boot application

```shell
mvn spring-boot:run
```

Connect WebSocket in Postman application

1. Change protocol to `ws`
2. Fill websocket URL `localhost:8080/{id}/{section}`
3. Click connect button

Send a message via REST API

```shell
curl --location 'localhost:8080/callback' \
--header 'Content-Type: application/json' \
--data '{
  "message": "SUCCESS",
  "contents": [
    "item1",
    "item2",
    "item3",
    "item4",
    "item5"
  ]
}'
```

Expect result in log console after request send message

```
Received message for WebSocket: {"message":"SUCCESS","contents":["item1","item2","item3","item4","item5"]}
Broadcasting message to all 1 sessions
```

**How to test the frontend project**

Run client website

```shell
yarn install
yarn dev
```
