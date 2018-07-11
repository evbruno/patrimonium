name := """patrimonium"""
organization := "etc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.4-P26-B4-SNAPSHOT"
//libraryDependencies += "org.webjars" % "bootstrap" % "4.0.0-1" exclude("org.webjars", "jquery")
//libraryDependencies += "org.webjars" % "jquery" % "3.3.1-1"
//libraryDependencies += "org.webjars" % "font-awesome" % "4.7.0"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "etc.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "etc.binders._"
