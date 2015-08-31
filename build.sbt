name := "polynome"

organization := "me.stojan"

publishMavenStyle := true

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"

homepage := Some(url("https://github.com/hf/polynome"))

licenses := Seq("MIT" -> url("https://github.com/hf/polynome/blob/master/LICENSE.txt"))

publishArtifact in Test := false

pomIncludeRepository := { _ => false }
