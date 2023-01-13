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
lazy val restServerFinatraDependencies = applicationDependencies ++ Seq(
  // REST Server
  "com.jakehschwartz" %% "finatra-swagger" % "20.4.1",
)

// Projects
lazy val global = project.aggregate(
  /* Clean Architecture */ template, domain, contract, application,
  /* Test */
  /* REST Server */ restServerFinatra
  /* Database */
) in file(".")

// // Clean Architecture
lazy val template = project.settings(name := "01_template") in file("./01_template")
lazy val domain = project.settings(name := "02_domain") in file("./02_domain")
lazy val contract = project.settings(name := "03_contract") in file("./03_contract")
lazy val application = project.settings(
  name := "04_application", libraryDependencies ++= applicationDependencies
) in file("./04_application")

// // Test
// Reserved: lazy val testProject = project.settings(name := "11_test") in file("./11_test")

// // REST Server
// Reserved: lazy val restServerPlay = project.settings(name := "21_play") in file("./21_play")
lazy val restServerFinatra = project.settings(
  name := "22_finatra", libraryDependencies ++= restServerFinatraDependencies
) in file("./22_finatra")
// Reserved: lazy val restServerAkka = project.settings(name := "23_akka") in file("./23_akka")

// // Database
// Reserved: lazy val databaseSlick = project.settings(name := "31_slick") in file("./31_slick")
