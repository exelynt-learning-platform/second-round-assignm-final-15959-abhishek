# E-Commerce Backend System (Spring Boot)

Production-ready e-commerce backend with JWT auth, product management, cart, order creation, and Paypal checkout.

## Tech Stack

- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- H2 (default local) / MySQL (production)
- Paypal Java SDK(sandbox)
- Swagger OpenAPI
- JUnit + Mockito

## Modules

- Auth: register and login (`/auth/register`, `/auth/login`)
- Products: CRUD APIs (`/products`)
- Cart: add/get/remove user cart items (`/cart`)
- Orders: create from cart and list user orders (`/orders`)
- Payments: Paypal checkout URL generation (`/payment/checkout`)
- ## Sample APIs

### Auth
POST /auth/register  
POST /auth/login  

### Products
GET /products  
POST /products (ADMIN)  
PUT /products/{id} (ADMIN)  
DELETE /products/{id} (ADMIN)  

### Cart
GET /cart  
POST /cart/add  
DELETE /cart/remove/{productId}  

### Orders
POST /orders  
GET /orders  

### Payments
POST /payment/checkout

## Run

1. Configure values in `src/main/resources/application.properties`
2. Run:

```bash
mvn spring-boot:run
```

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Security

- Passwords are hashed with BCrypt.
- JWT is required for protected endpoints.
- Product create/update/delete is restricted to `ADMIN`.

## Notes

- Default DB is in-memory H2 for quick local execution.
- For MySQL, switch datasource properties in `application.properties`.
-PayPal client ID and secret should be configured in application.properties (Sandbox credentials).
