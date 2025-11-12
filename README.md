# IoT Gateway - Spring Boot (Full)

Projeto: backend Spring Boot que atua como gateway IoT.
Recebe mensagens MQTT de sensores (ESP32), persiste em MongoDB e expõe APIs REST.

## O que foi adicionado nesta versão
- DTOs e validações (Bean Validation)
- Camada de Service e mapeamento DTO <-> entity
- Endpoints com paginação e filtros por sensor, tipo e intervalo de tempo
- Testes unitários com JUnit e Mockito
- Dockerfile e docker-compose com serviço `app` (build da imagem)
- SVG de logo e diagrama arquitetural
- Scripts para simular sensores via MQTT

## Como rodar (Docker - recomendado)
1. Tenha Docker e Docker Compose instalados.
2. Na pasta do projeto, execute:
   ```bash
   docker compose up --build
   ```
3. A API ficará disponível em `http://localhost:8080/api/sensors`.

## Endpoints principais
- `GET /api/sensors` - lista leituras (parâmetros opcionais: `sensorId`, `type`, `from`, `to`, `page`, `size`)
- `GET /api/sensors/<built-in function id>` - busca por id

## Exemplo de uso
```bash
curl 'http://localhost:8080/api/sensors?page=0&size=10&sensorId=esp-1&from=1690000000000&to=1699999999999'
```

## Desenvolvimento local (sem Docker)
1. Configure `src/main/resources/application.yml` (MongoDB host/port, MQTT broker).
2. Rode `mvn clean package && mvn spring-boot:run`

## Testes
```bash
mvn test
```

## Arquivos úteis
- `LINKEDIN_POST.txt` - texto sugerido para LinkedIn.
- `logo.svg` - logo simples (gear + IoT).
- `architecture.svg` - diagrama arquitetural.
