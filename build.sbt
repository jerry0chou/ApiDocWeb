name := "ApiDocWeb"

version := "1"

scalaVersion := "2.12.10"

enablePlugins(ScalaJSPlugin)

scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"
libraryDependencies += "com.thoughtworks.binding" %%% "dom" % "latest.release"
libraryDependencies += "com.thoughtworks.binding" %%% "binding" % "latest.release"
libraryDependencies += "com.thoughtworks.binding" %%% "futurebinding" % "latest.release"
libraryDependencies += "org.querki" %%% "jquery-facade" % "1.2"
libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.8.0"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)