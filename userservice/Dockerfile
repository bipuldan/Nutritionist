FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=nutritionalist
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/nutritionalist
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java", "-jar", "userservice-1.0.jar"]
