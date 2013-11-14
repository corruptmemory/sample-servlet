import sbt._
import sbt.Keys._

object SampleSprayServlet extends Build {
  val Version = "0.0.1-SNAPSHOT"

  lazy val sampleServlet = Project(
    id = "sample-servlet",
    base = file("."),
    settings = defaultSettings ++ Seq(
      name := "sample-servlet",
      resolvers += "spray repo" at "http://repo.spray.io",
      libraryDependencies ++= Dependencies.sampleServlet
    )
  )

  lazy val defaultSettings: Seq[Setting[_]] = Defaults.defaultSettings ++ com.earldouglas.xsbtwebplugin.WebPlugin.webSettings ++ Seq(
    organization := "com.typesafe",
    version := Version,
    publishMavenStyle := false,
    scalaVersion := "2.10.3",
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-feature", "-Xlint")
  )

  object Dependencies {
    object V {
      val akkaVersion = "2.2.3"
      val sprayVersion = "1.2-RC3"
    }

    lazy val jetty = "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
    lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % V.akkaVersion
    lazy val spray = Seq(
      "io.spray" % "spray-http" % V.sprayVersion,
      "io.spray" % "spray-util" % V.sprayVersion,
      "io.spray" % "spray-servlet" % V.sprayVersion
    )
    lazy val sampleServlet = Seq(akkaActor,jetty) ++ spray
  }
}
