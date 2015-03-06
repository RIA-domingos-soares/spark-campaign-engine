name := "spark-campaign-engine"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.2.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.2.0"

//libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

//libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.9.16"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4"

fork in test := false

parallelExecution in Test := false


