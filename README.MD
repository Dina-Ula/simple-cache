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

## Init the cache

`The simple cache is a lazy cache so it needs to be initialized`

#### Exec the container

kubectl exec -it simple-cache-XX-XX -- bash

#### Run the curl

curl http://localhost:8080/getData

## Check if the cache is evicted

After eviction, the main application should populate the cache again.
To populate the cache, the curl http://localhost:8080/getData should be executed from the main container.

Check the sidecar log to validate the cache is evicted by the sidecar.
kubectl logs -f simple-cache-XX-XX -c simple-cache-sidecar

## Error(s)

`404 Not Found from DELETE http://localhost:8088/actuator/caches/dataCache`

The cache is only initialized after the curl http://localhost:8080/getData is manually
executed from the main container. Until, it is initialized the sidecar will throw this exception.
