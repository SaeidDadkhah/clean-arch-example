name := "clean-arch-example"
organization := "com.github.saeiddadkhah"
version := "0.0.2"

scalaVersion := "2.12.14"

// Projects
lazy val global = project.in(file(".")).aggregate(template, domain, contract, application)

lazy val template = project.in(file("./01_template")).settings(name := "01_template")
lazy val domain = project.in(file("./02_domain")).settings(name := "02_domain")
lazy val contract = project.in(file("./03_contract")).settings(name := "03_contract")
lazy val application = project.in(file("./04_application")).settings(name := "04_application")
