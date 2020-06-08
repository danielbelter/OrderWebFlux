FROM openjdk:11-oracle
EXPOSE 8080
LABEL maintainer="daniel belter"
ARG JAR_FILE=webapp.jar
ADD ./target/${JAR_FILE} ${JAR_FILE}
ENTRYPOINT ["java", "-jar", "webapp.jar"]