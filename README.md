# Clean Arch Example

An implementation of Clean Architecture in Scala. We will implement a simple blog application using Clean Architecture in Scala.

## Subprojects:

I will walk through the following projects to show the process of creating a Scala Application using Clean Architecture:

1. Template
2. Domain
3. Contract
4. Application

### 1. Template

Directories and abstraction of `contract.service.Service`.

### 2. Domain

Design of classes and implementation of simple class-related use cases.

### 3. Contract

Signature of services and callbacks.

* Services should extend `c.s.Service` and have only one public method which is `c.s.Service.call`.
* Callbacks should contain only the abstraction of CRUD operations without any logic.
* Callbacks do not take execution context as a parameter and should take one from their parent module _(R.T. #4 Application)_.

### 4. Application

Implementation of repositories, use cases, and modules.

* Repositories should implement callbacks and have only public members which their parent has.
* Use cases should implement services and should have only one public member which is `c.s.Service.call`.
* Use cases should use instances of callbacks **_(NOT repositories)_**.
* All configurations should be placed in `modules` package.
* There are three essential modules
    1. Loading the config and validating it happens in `modules.ConfigModule`.
    1. Callbacks should be bound to repositories in `modules.CallbackModule`.
    1. Services should be bound to use cases in `modules.ServiceModule`.
* Each type of external data source and entry should have exactly one module.
    * All repositories using database should extend `modules.DatabaseModule`
    * All repositories using REST API and a special HTTP client should extend `modules.RESTModule`

Here are some tips about config files.
* An important **security** point is **_NOT_** to commit config files.
Put some entries to ignore config files in `.gitignore` file.
    * `application.conf`
    * `application.staging*.conf`
    * `application.prodcution*.conf`
* Store all config files in one directory i.e. the project root, `resources`.
I recommend using `resources` directory.
* It is essential to specify the template of config file, and I recommend committing `application.template.conf` with dummy values.
* There are multiple ways to specify the config file which we want to run the application with.
We are using `config` library of `com.typesafe` to load configs.
    * It utilises [HOCON](https://github.com/lightbend/config/blob/main/HOCON.md) files to load configs.
    * Expectedly, this library offers `c.t.c.ConfigFactory.parseFile` function to parse config file.
    You may set an environment variable to specify the config file and load it using this function.
    * A more elegant method is to use `-DConfig.file` JVM option and use this function
    `ConfigFactory.load().withFallback(ConfigFactory.defaultApplication()).resolve`.
