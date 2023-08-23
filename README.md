# chat-app-aws-lambda-mongodb-redis-rabbitmq
This is a simple POC backend of a chat application built with AWS Lambda, MongoDB, Redis, RabbitMQ.

## How to compile and run locally
1. Download AWS CLI
2. Download redis and redis-cli
3. Download Docker
4. Download MongoDB
5. Move into the `serverless-backend` folder.
6. Type `mvn clean package` -> `sam build` -> `sam local invoke ServerlessBackendFunction -e event.json`.
