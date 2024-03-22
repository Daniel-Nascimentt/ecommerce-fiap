FROM maven:3.8.4 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17

ENV HOST_CLIENTES=http://service-clientes:8081/ms-clientes
ENV HOST_CARRINHO=http://service-carrinho:8083/ms-carrinho
ENV HOST_PAGAMENTOS=http://service-pagamentos:8084/ms-pagamento
ENV HOST_GT_ITENS=http://service-itens:8082/ms-gt-itens
ENV HOST_AUTENTICACAO=http://service-autenticacao:8085/ms-autenticacao

WORKDIR /app

COPY --from=builder /app/target/*.jar ms-gateway.jar

CMD ["java", "-jar", "ms-gateway.jar"]