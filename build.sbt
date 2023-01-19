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
lazy val exceptionsDependencies = applicationDependencies
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
  /* Improvements */
  /* Test */
  /* REST Server */ restServerFinatra,
  /* Database */
) in file(".")

// // Clean Architecture
lazy val template = project.settings(name := "11_template") in file("./11_template")

/** Read [[template]] before reading this project. */
lazy val domain = project.settings(name := "12_domain") in file("./12_domain")

/** Read [[domain]] before reading this project. */
lazy val contract = project.settings(name := "13_contract") in file("./13_contract")

/**
 * All following projects use this project, so I strongly recommend you to read this project before any other project.
 *
 * Read [[contract]] before reading this project. */
lazy val application = project.settings(name := "14_application", libraryDependencies ++= applicationDependencies) in file("./14_application")

// // Improvements
/** Read [[application]] before reading this project. */
lazy val exceptions = project.settings(name := "21_exceptions", libraryDependencies ++= exceptionsDependencies) in file("./21_exceptions")
// Reserved: lazy val futureUtils = project.settings(name := "21_future_utils") in file("./21_future_utils")

// // Test
// Reserved: lazy val testScalatest = project.settings(name := "31_test_scalatest") in file("./31_test_scalatest")

// // Dependency Injection
/** Read [[application]] before reading this project. */
lazy val dependencyInjectionGuice =
  project.settings(name := "41_dependency_injection_guice", libraryDependencies ++= dependencyInjectionDependencies) in file("./41_dependency_injection_guice")

// // REST Server
// Reserved: lazy val restServerPlay = project.settings(name := "51_play") in file("./51_play")
/** Read [[dependencyInjectionGuice]] before reading this project. */
lazy val restServerFinatra = project.settings(name := "52_finatra", libraryDependencies ++= restServerFinatraDependencies) in file("./52_finatra")
// Reserved: lazy val restServerAkka = project.settings(name := "53_akka") in file("./53_akka")

// // Database
// Reserved: lazy val databaseSlick = project.settings(name := "61_slick") in file("./61_slick")
// Reserved: lazy val databaseScalikeJDBC = project.settings(name := "62_scalikejdbc") in file("./62_scalikejdbc")
