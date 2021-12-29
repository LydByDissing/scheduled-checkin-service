export SERVICE_NAME=scheduled-checkin-service

docker rm -f $SERVICE_NAME | true

docker run \
        -d \
        --name scheduled-checkin-service \
        --restart always \
         $SERVICE_NAME
