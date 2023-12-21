
# TEST GET PRICING TEST

Solución de software para prueba de ingreso a Between

la solución crear un archivo de base de datos nombrado ```pricing.mv.db```


## Run Locally

Clone the project

```bash
  git clone https://github.com/josefragozo/price-api-test
```

Go to the project directory

```bash
  cd api-test-bwn
```

Install dependencies Windows

```bash
  ./gradlew clean build
```

Run tests

```bash
  ./gradlew clean test
```

Start the server

```bash
  ./gradlew bootRun
```


## API Reference

#### Get price

```http
  GET /price
```

| Parameter               | Type       | Description                      |
|:------------------------|:-----------|:---------------------------------|
| `identificadorCadena`   | `numerico` | **Required**. id de cadena       |
| `identificadorProducto` | `numerico` | **Required**. id de producto     |
| `fechaAplicacion`       | `fecha`    | **Required**. fecha de solicitud |
## Documentation

curl example:
```bash
curl -X 'GET' \
  'http://localhost:8080/price?identificadorCadena=1&identificadorProducto=35455&fechaAplicacion=2020-06-16T21%3A00%3A00' \
  -H 'accept: */*'
```

response:

```json
{
  "identificadorDeProducto": 35455,
  "cadena": "ZARA",
  "tarifa": 35,
  "fechaDeAplicacion": "2023-12-21T20:41:06.590Z",
  "fechaFinalPrecio": "2023-12-21T20:41:06.590Z"
}
```
enlace a swagger cuando el servicio esta levantado y corriendo:
```http://localhost:8080/swagger-ui/index.html#/price-controller```

swagger definition

```json
{
  "openapi":"3.0.1",
  "info":{
    "title":"OpenAPI definition",
    "version":"v0"
  },
  "servers":[
    {
      "url":"http://localhost:8080",
      "description":"Generated server url"
    }
  ],
  "paths":{
    "/price":{
      "get":{
        "tags":[
          "price-controller"
        ],
        "summary":"Obtiene el precio de un producto para una fecha especifica",
        "operationId":"getPrice",
        "parameters":[
          {
            "name":"identificadorCadena",
            "in":"query",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int32"
            }
          },
          {
            "name":"identificadorProducto",
            "in":"query",
            "required":true,
            "schema":{
              "type":"integer",
              "format":"int32"
            }
          },
          {
            "name":"fechaAplicacion",
            "in":"query",
            "required":true,
            "schema":{
              "type":"string",
              "format":"date-time"
            }
          }
        ],
        "responses":{
          "200":{
            "description":"objeto con la informacion de precio e identificadores",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/PriceResponse"
                }
              }
            }
          },
          "400":{
            "description":"retorna un mensaje de error al no encontra la informacion solicitada",
            "content":{
              "application/json":{
                "schema":{
                  "$ref":"#/components/schemas/PriceResponse"
                }
              }
            }
          }
        }
      }
    }
  },
  "components":{
    "schemas":{
      "PriceResponse":{
        "type":"object",
        "properties":{
          "identificadorDeProducto":{
            "type":"integer",
            "format":"int32"
          },
          "cadena":{
            "type":"string"
          },
          "tarifa":{
            "type":"number",
            "format":"double"
          },
          "fechaDeAplicacion":{
            "type":"string",
            "format":"date-time"
          },
          "fechaFinalPrecio":{
            "type":"string",
            "format":"date-time"
          }
        }
      }
    }
  }
}
```
