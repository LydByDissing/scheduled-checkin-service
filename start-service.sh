export SERVICE_NAME=scheduled-checkin-service

docker rm -f $SERVICE_NAME | true
docker run \
        -d \
        --name scheduled-checkin-service \
        --restart always \
        --log-driver=json-file --log-opt max-size="10m" --log-opt max-file="10" \
         $SERVICE_NAME
