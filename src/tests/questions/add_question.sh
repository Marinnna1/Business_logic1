#!bin/bash

curl -X PUT \
  -H "Content-Type:application/json" \
  -d '{"creatorId": "4", "head": "2", "body": "ddddd", "evaluated": false, "tag": "JAVA", "token": "ad30b44e1e057a2070dacac94607470d"}' \
  http://localhost:8081/questions/add