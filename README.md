# cqrs-design-pattern

A CQRS demo split into a **product-command-service** (writes, port `9191`) and a **product-query-service** (reads, port `9292`), backed by separate MySQL databases and synchronized via Kafka.

## Run with Docker Compose

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/) and Docker Compose v2 (`docker compose`)

### Start the stack

From the project root, build the images and start all services (Zookeeper, Kafka, both MySQL databases, and both services):

```bash
docker compose up --build
```

To run in the background (detached mode):

```bash
docker compose up --build -d
```

The root `docker-compose.yml` includes the per-service compose files, so this single command brings up the entire stack.

### Services and ports

| Service                   | URL / Port                  | Description                       |
| ------------------------- | --------------------------- | --------------------------------- |
| product-command-service   | `http://localhost:9191`     | Handles create/update/delete      |
| product-query-service     | `http://localhost:9292`     | Handles reads                     |
| Kafka                     | `localhost:9092`            | Event bus between the services    |
| MySQL (write)             | `localhost:3308`            | Command-side database             |
| MySQL (read)              | `localhost:3310`            | Query-side database               |

### Check status and logs

```bash
docker compose ps
docker compose logs -f
```

### Stop the stack

```bash
docker compose down
```

To also remove the database volumes:

```bash
docker compose down -v
```

API


### CreateProduct Event

```
curl --location 'http://localhost:9191/products' \
--header 'Content-Type: application/json' \
--data '{
    "type": "CreateProduct",
    "product": {
        "name": "Books",
        "description": "KK publication",
        "price": 999
    }
}'
```
### UpdateProduct Event

```
curl --location --request PUT 'http://localhost:9191/products/1' \
--header 'Content-Type: application/json' \
--data '{
    "type": "UpdateProduct",
    "product": {
        "id": 1,
        "name": "Watch",
        "description": "Samsung latest model",
        "price": 58000.0
    }
}'
```

### DeleteProduct Event

```
curl --location --request DELETE 'http://localhost:9191/products/1'
```

### GetProducts Event

```
curl --location --request GET 'http://localhost:9292/products' --header 'Content-Type: application/json'
```

