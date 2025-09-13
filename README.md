## Requirements

- JDK 17+

- Spring Boot (with WebSocket starter)

- Kotlin 1.9.25

## Set an image path
When you connect to TouchDesigner, you should set an image path(To save an image snapshot)

```
src/main/kotlin/com/pjhn/td/webSocket/WebSocketHandler.kt

...

val imagePath = Paths.get(//Set your image path)

...

```

## Run
```
./gradlew bootRun
```

onStarted, connect to  `ws://localhost:8080`
##

