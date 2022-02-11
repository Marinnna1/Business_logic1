#!bin/bash

curl -X DELETE \
  -H "Content-Type:application/json" \
  -d '{"id":2, "token": "ad30b44e1e057a2070dacac94607470d"}' \
  http://localhost:8081/questions/delete