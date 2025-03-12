# Kafka Service

## Overview
Kafka is a distributed event streaming platform used for building real-time data pipelines and streaming applications. It provides a high-throughput, fault-tolerant, and scalable messaging system that enables the communication between different services.

## How Kafka Works
Kafka operates based on the following key components:

### 1. **Producers**
   - Producers publish (write) data to Kafka topics.
   - Data is stored in partitions for scalability.

### 2. **Topics and Partitions**
   - A topic is a logical channel where messages are published.
   - Each topic is divided into multiple partitions for parallel processing.
   - Messages within a partition are ordered.

### 3. **Brokers**
   - Brokers are Kafka servers that store messages and serve consumers.
   - Kafka clusters consist of multiple brokers.

### 4. **Consumers**
   - Consumers subscribe to topics and read messages.
   - Each consumer belongs to a consumer group.

### 5. **Zookeeper**
   - Kafka uses Zookeeper for managing cluster metadata and leader election.

## Running Kafka Service with Docker Compose
To run Kafka using Docker Compose, use the following `docker-compose.yml` file:

```yaml
version: '3.8'

services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```

### Steps to Run Kafka
1. **Start Kafka and Zookeeper**
   ```sh
   docker-compose up -d
   ```
   This will start Zookeeper and Kafka in detached mode.

2. **Create a Kafka Topic**
   ```sh
   docker exec -it kafka kafka-topics --create --topic test-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
   ```

3. **List Topics**
   ```sh
   docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092
   ```

4. **Produce Messages**
   ```sh
   docker exec -it kafka kafka-console-producer --broker-list localhost:9092 --topic test-topic
   ```
   Type a message and press Enter to send it.

5. **Consume Messages**
   ```sh
   docker exec -it kafka kafka-console-consumer --bootstrap-server localhost:9092 --topic test-topic --from-beginning
   ```

## Features Handled in Kafka Service
Kafka can be used for various functionalities in your system:

### ✅ **Real-time Event Processing**
Kafka enables real-time event-driven architecture by passing messages between microservices efficiently.

### ✅ **Logging and Monitoring**
Kafka can be used for log aggregation and monitoring in distributed systems.

### ✅ **Asynchronous Communication**
Microservices can communicate asynchronously using Kafka, improving system scalability and performance.

### ✅ **Message Queueing**
Kafka acts as a reliable message broker for event-driven applications, ensuring messages are delivered even during failures.

### ✅ **Streaming Analytics**
Kafka can be integrated with tools like Apache Flink or Apache Spark for real-time data analytics.

### ✅ **Decoupling Services**
Kafka helps in decoupling services in a microservices architecture, making them more scalable and fault-tolerant.

## Managing Kafka Topics and Consumers

### 🔹 **Check Consumer Groups**
```sh
docker exec -it kafka kafka-consumer-groups --bootstrap-server localhost:9092 --list
```

### 🔹 **Describe a Consumer Group**
```sh
docker exec -it kafka kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group <group-name>
```

### 🔹 **Reset Consumer Offsets**
```sh
docker exec -it kafka kafka-consumer-groups --bootstrap-server localhost:9092 --group <group-name> --reset-offsets --to-earliest --execute
```

## Troubleshooting Kafka Logs
If Kafka generates excessive logs, check the following:

- **Consumer Restarting Frequently:** Ensure consumers are running consistently.
- **No Committed Offsets:** Make sure consumers commit offsets properly.
- **Reduce Kafka Log Level:**
  ```yaml
  logging:
    level:
      org.apache.kafka: ERROR
  ```

## Stopping Kafka and Zookeeper
To stop the Kafka and Zookeeper services:
```sh
docker-compose down
```

## Conclusion
Kafka is a powerful distributed messaging system used for event-driven architectures, microservices, logging, and real-time analytics. With Docker, you can easily set up and manage Kafka for your applications.

---
**Author:** Prasanth Kumar Ranga  
**Designation:** Technical Lead

