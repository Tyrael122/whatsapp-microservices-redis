# Real-Time Chat Application

This project is a real-time chat application built using a **microservices architecture**. It leverages **Redis** for real-time message delivery and caching, and **MongoDB** for storing chat history and user profiles. The backend is implemented in **Java** using **Spring Boot**.

---

## Features

- **Message History**: Stores chat history in MongoDB for persistent storage.
- **Caching**: Utilizes Redis for caching of message history.
- **Load Testing**: Includes K6 load tests to measure performance with and without Redis.
- **Microservices Architecture**: The application is divided into multiple microservices for better scalability and maintainability.
- **RESTful APIs**: Exposes RESTful APIs for interaction with the services.

---

## Microservices

### 1. **User Microservice**
Handles user creation and user details.
  - **Endpoints**:
    - `POST /users`
    - `GET /users/{id}`

### 2. **Chat Microservice**
Manages chat rooms and user participation.
  - **Endpoints**:
    - `POST /chats`
    - `POST /chats/{chatId}/users`
    - `GET /chats`
    - `GET /chats/{chatId}/users`

### 3. **Message Routing Microservice**
Handles message reception and retrieval.
- **Endpoints**:
    - `POST /message-router/chat/{chatId}/send`
    - `GET /message-router/chat/{chatId}/messages`
---

## Technologies

- **Backend**: Java with Spring Boot
- **Database**: MongoDB for chat history and user profiles
- **Caching**: Redis for caching
- **Load Testing**: K6 for performance testing

---

## Performance Comparison
The load tests are designed to compare the performance of the system with and without Redis. The results will help in understanding the impact of Redis on the message retrieval.

### Test description
A chat room with 2 users is created, and 1000 messages are sent to it. Then, the message history is retrieved 10000 times.

#### Without Redis:
- **Average Response Time**: 41.6 ms

![Without Redis K6 result](https://github.com/user-attachments/assets/790afc1e-e206-4471-9847-739369fd962f)

#### With Redis:
- **Average Response Time**: 11.66 ms

![With redis K6 result](https://github.com/user-attachments/assets/69fd78b4-3d62-459d-b967-f6a7af9fbce1)

---

## Future Enhancements

- Integrate with a messaging service like Kafka or RabbitMQ for message queuing.
- Implement WebSocket for real-time bidirectional communication.
