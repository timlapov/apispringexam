POST http://localhost:8081/api/users/add
{
"name": "Bruno",
"email": "bruno@bruno.com",
"password": "1234"
}

POST http://localhost:8081/api/events/add
{
"name": "1111",
"description": "22222222",
"ownerId": "0a15290b-e58b-435d-b6b3-d5c3fc80267b"
}

GET http://localhost:8081/api/events/60beb4cb-adb8-4464-9940-d5061aeeb555/expenses 
GET http://localhost:8081/api/events/60beb4cb-adb8-4464-9940-d5061aeeb555/expenses?amount_gt=10&amount_lt=50

GET http://localhost:8081/api/events/60beb4cb-adb8-4464-9940-d5061aeeb555/balance



