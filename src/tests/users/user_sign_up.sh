#!bin/bash

curl -X POST \
  -H "Content-Type:application/json" \
  -d '{"login": "cd", "password": "password", "email":"ssss@mail.ru"}' \
  http://localhost:8081/user/reg