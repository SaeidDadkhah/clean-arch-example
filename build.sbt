name := "clean-arch-example"
organization := "com.github.saeiddadkhah"
version := "0.0.3"

scalaVersion := "2.12.14"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-encoding", "utf8")

// Dependencies
lazy val applicationDependencies = Seq(
  // Config
  "com.typesafe" % "config" % "1.4.0",
)

// Projects
lazy val global = project.aggregate(template, domain, contract, application) in file(".")

lazy val template = project.settings(name := "01_template") in file("./01_template")
lazy val domain = project.settings(name := "02_domain") in file("./02_domain")
lazy val contract = project.settings(name := "03_contract") in file("./03_contract")
lazy val application = project.settings(
  name := "04_application", libraryDependencies ++= applicationDependencies
) in file("./04_application")
