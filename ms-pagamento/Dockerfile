FROM maven:3.8.4 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=builder /app/target/*.jar ms-pagamento.jar

ENV PG_HOST=meu-postgres
ENV PG_USER=postgres
ENV PG_PASSWORD=postgres
ENV API_SECRET=7GANwY75ax
ENV MS_CARRINHO_URL=service-carrinho
ENV MS_CLIENTES_URL=service-clientes

CMD ["java", "-jar", "ms-pagamento.jar"]