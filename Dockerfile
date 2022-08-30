FROM openjdk:8u181

WORKDIR /opt

COPY target/ /opt/target

ENTRYPOINT ["sh", "./target/pack/bin/server"]
