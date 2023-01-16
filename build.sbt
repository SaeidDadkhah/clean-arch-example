name := "clean-arch-example"
organization := "com.github.saeiddadkhah"
version := "0.0.4"

scalaVersion := "2.12.17"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-encoding", "utf8")

// Dependencies
lazy val applicationDependencies = Seq(
  // Config
  "com.typesafe" % "config" % "1.4.2",
)
lazy val dependencyInjectionDependencies = applicationDependencies ++ Seq(
  // Dependency Injection
  "com.google.inject" % "guice" % "5.1.0",
)
lazy val restServerFinatraDependencies = applicationDependencies ++ Seq(
  // REST Server
  "com.jakehschwartz" %% "finatra-swagger" % "20.4.1",
)

// Projects
lazy val global = project.aggregate(
  /* Clean Architecture */ template, domain, contract, application,
  /* Test */
  /* REST Server */ restServerFinatra,
  /* Database */
) in file(".")

// // Clean Architecture
lazy val template = project.settings(name := "01_template") in file("./01_template")

/** Read [[template]] before reading this project. */
lazy val domain = project.settings(name := "02_domain") in file("./02_domain")

/** Read [[domain]] before reading this project. */
lazy val contract = project.settings(name := "03_contract") in file("./03_contract")

/**
 * All following projects use this project, so I strongly recommend you to read this project before any other project.
 *
 * Read [[contract]] before reading this project. */
lazy val application = project.settings(name := "04_application", libraryDependencies ++= applicationDependencies) in file("./04_application")

// // Test
// Reserved: lazy val testScalatest = project.settings(name := "11_test_scalatest") in file("./11_test_scalatest")

// // Dependency Injection
/** Read [[application]] before reading this project. */
lazy val dependencyInjection =
  project.settings(name := "21_dependency_injection_guice", libraryDependencies ++= dependencyInjectionDependencies) in file("./21_dependency_injection_guice")

// // REST Server
// Reserved: lazy val restServerPlay = project.settings(name := "31_play") in file("./31_play")
/** Read [[dependencyInjection]] before reading this project. */
lazy val restServerFinatra = project.settings(name := "32_finatra", libraryDependencies ++= restServerFinatraDependencies) in file("./32_finatra")
// Reserved: lazy val restServerAkka = project.settings(name := "33_akka") in file("./33_akka")

// // Database
// Reserved: lazy val databaseSlick = project.settings(name := "41_slick") in file("./41_slick")
// Reserved: lazy val databaseScalikeJDBC = project.settings(name := "42_scalikejdbc") in file("./42_scalikejdbc")
