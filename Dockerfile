FROM openjdk:8u181

WORKDIR /opt

COPY target/ /opt/target

EXPOSE 8080

ENTRYPOINT ["sh", "./target/pack/bin/server"]
