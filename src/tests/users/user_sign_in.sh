#!bin/bash

curl -X POST \
  -H "Content-Type:application/json" \
  -d '{"login": "2", "password": "2"}' \
  http://localhost:8081/user/login