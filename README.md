# IoT Gateway - Spring Boot

Backend em **Spring Boot** que atua como gateway IoT.
Recebe mensagens MQTT de sensores (ESP32), persiste em MongoDB e expõe APIs REST.

## Recursos
- MQTT subscriber (Eclipse Paho)
- Spring Data MongoDB
- Endpoints REST para consulta de leituras
- Docker Compose com MongoDB e Mosquitto (broker)
- Exemplo de script Python para simular sensores via MQTT

## Rodando o projeto (local)
1. Tenha Java 21 e Maven instalados.
2. Inicie o Docker Compose: `docker compose up -d`
3. Ajuste `src/main/resources/application.yml` se necessário.
4. Build e run: `mvn clean package && mvn spring-boot:run`

## Teste MQTT (simulador)
Use o script `tools/mqtt_publish.py` para enviar mensagens de exemplo ao tópico `sensors/+/data`.

## Link para postar no LinkedIn
Veja `LINKEDIN_POST.txt` com um texto pronto e hashtags.
