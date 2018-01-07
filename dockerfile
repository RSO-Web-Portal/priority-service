FROM openjdk:8

RUN mkdir /priority

WORKDIR /priority

COPY . ./priority
ADD . /priority

EXPOSE 8087

CMD ["java", "-jar", "target/priority-service-2.5.0-SNAPSHOT.jar"]