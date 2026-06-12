# cqrs-design-pattern

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

