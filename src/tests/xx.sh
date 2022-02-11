#!bin/bash

curl -X POST \
  -H "Content-Type:application/json" \
  -d '{"login": "pp"}' \
  http://localhost:8081/user/login