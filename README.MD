# Demo spring simple cache and spring actuator cache eviction

## Build main container image and push it to the dockerhub

### Run it from the simple-cache-main module path

mvn clean -DskipTests install && docker build -t devinaction/simple-cache-main --build-arg JAR_FILE=target/simple-cache-main-0.0.1-SNAPSHOT.jar . && docker push devinaction/simple-cache-main

## Build sidecar container image and push it to the dockerhub

### Run it from the simple-cache-sidecar module path

mvn clean -DskipTests install && docker build -t devinaction/simple-cache-sidecar --build-arg JAR_FILE=target/simple-cache-sidecar-0.0.1-SNAPSHOT.jar . && docker push devinaction/simple-cache-sidecar

## Delete deployment

kubectl delete deployments.apps simple-cache

## Apply deployment

kubectl apply -f deployment.yaml