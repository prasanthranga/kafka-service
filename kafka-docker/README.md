# 🚀 Kafka & Zookeeper Setup with Docker Compose  

This guide provides step-by-step instructions to run Kafka and Zookeeper using Docker Compose.

---

## 📌 Prerequisites  
Ensure you have the following installed on your system:
- [Docker](https://www.docker.com/get-started)  
- [Docker Compose](https://docs.docker.com/compose/install/)  

To verify the installations, run:
```sh
docker --version
```
```sh
docker-compose --version
```

---

## 🛠 How to Run Kafka & Zookeeper  

### 1️⃣ Navigate to the Directory  
Move into the folder where your `docker-compose.yml` file is located:
```sh
cd kafka-docker  # Change this to your actual directory
```

### 2️⃣ Start Kafka & Zookeeper  
Run the following command to start Kafka & Zookeeper as Docker containers:
```sh
docker-compose up -d
```
- `-d` runs the containers in **detached mode** (in the background).  

### 3️⃣ Verify Running Containers  
Check if both Kafka & Zookeeper are running:
```sh
docker ps
```
You should see **two containers**:  
✅ `kafka`  
✅ `zookeeper`

---

## 🛑 Stopping the Containers  
To stop the containers, run:
```sh
docker-compose down
```
This will **stop & remove** the containers.

---

## 📡 How to Connect Kafka in Spring Boot  
In your `application.yml`, configure Kafka:
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
```
This ensures your Spring Boot application connects to Kafka.

---

## 🔎 Check Kafka Topics  
To list Kafka topics, use:
```sh
docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092
```

---

## 🔥 Need Kafka UI?  
Would you like to add **Kafka UI** (e.g., `kafdrop` or `AKHQ`) to easily manage topics? Let me know! 🚀