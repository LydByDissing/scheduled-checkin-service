#!/bin/bash
export HEARTBEAT_URI=https://receive-heartbeat-service.lydbydissing.workers.dev/heartbeat
export HEARTBEAT_LOCATION=Copenhagen

exec java -DHEARTBEAT_URI="${HEARTBEAT_URI}" -DHEARTBEAT_LOCATION="${HEARTBEAT_LOCATION}" -jar /bin/scheduled-checkin-service.jar
