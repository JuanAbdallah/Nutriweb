# Usar a imagem base do OpenJDK
FROM openjdk:11-jre-slim

# Definir o diretório de trabalho no container
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven para o container
COPY target/*.jar app.jar

# Expor a porta que será usada pela aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
