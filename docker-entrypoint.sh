#!/bin/bash
exec java -DHEARTBEAT_URI="${HEARTBEAT_URI}" -DHEARTBEAT_LOCATION="${HEARTBEAT_LOCATION}" -jar /bin/scheduled-checkin-service.jar
