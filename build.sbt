name := "MyFirstProject"

version := "0.1"

scalaVersion := "2.13.8"
enablePlugins(PackPlugin)

val akkaActorsVersion = "2.6.16"
val akkaStreamsVersion = "2.6.16"
val akkaHttpVersion = "10.2.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaActorsVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaStreamsVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.datastax.cassandra" % "cassandra-driver-core" % "3.5.1",

  //JWT: token
  "com.pauldijou" %% "jwt-spray-json" % "5.0.0"

)