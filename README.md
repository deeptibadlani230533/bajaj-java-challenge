# Bajaj Finserv Health - BFHL API

Spring Boot REST API built for the Acropolis Campus Hiring challenge.

## Endpoint

POST /bfhl

## What it does

Takes an array of mixed strings and returns:
- Separated odd numbers, even numbers, alphabets, special characters
- Sum of all numbers
- Alphabets in uppercase
- Concat string (reversed alphabets in alternating caps)

## Run locally

```bash
mvn spring-boot:run
```

## Test

```bash
curl -X POST http://localhost:8080/bfhl \
-H "Content-Type: application/json" \
-d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

## Stack
Java 17, Spring Boot 3
