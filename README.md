# Conduit Blog Backend Test Sample

> Sample automated test of backend. Application under test: https://react-redux.realworld.io/

### Table of contents
- [📝 Approaches](#-approaches)
- [🗄️ Project structure](#-project-structure)
- [⚙️ Development stack](#-development-stack)
- [🔧 Requirements](#-requirements)
- [💻 How to run in your local machine](#-how-to-run-in-your-local-machine)

### 📝 Approaches

- These are end-to-end tests of APIs. However, usually it is possible to replace the e2e tests by unit, contract and/or integration tests with the same level of confidence, mainly if our project uses Spring Boot.
- I would mock the repositories. However, in this e2e case, I decided to hardcode 1 user in the e2e environment and take strong care of this data.
  The reason for this is that I would not have to create a new user (and after delete it) on every test.
  If the registration API fails then we would see a false result for all of the endpoints, and could not test/deploy them.
- Usually I use [Lombok](https://projectlombok.org/) but that would require certain configurations to make it work in an IDE. 
- I could have used [Hamcrest](http://hamcrest.org/) for the matchers, but I really do not have a preference on this, so I went on the traditional way this time.
- [Object Mother](https://martinfowler.com/bliki/ObjectMother.html)
> It could make use of [fluent builder](https://reflectoring.io/objectmother-fluent-builder/) too but it would be overengineering for this application.
- The tests would be located inside the application's repo, see my [article](https://medium.com/justeattakeaway-tech/where-to-save-test-automation-scripts-c19642a07cb3) on the reasoning.

### 🗄️ Project structure

```
backend
    test/java/com.sample
        articles                    <-- Functionality
            entities                <-- Data entities for request and response
            objectMothers           <-- Object Mother design pattern
        profiles                    <-- Functionality
        users                       <-- Functionality
        utils
            Utils                   <-- Utilities such as getBaseUrl
```

### ⚙️ Development stack

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/)
- [JUnit 5](https://junit.org/junit5/)
- [REST Assured](https://rest-assured.io/)

### 🔧 Requirements

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/)
- You must have created a user in the application: https://react-redux.realworld.io/

### 💻 How to run in your local machine

Given you installed the [required tools](#-requirements), do the steps below.

1. Via terminal, clone the repo:

```sh
git clone https://github.com/jonatask/conduit-blog-backend-test-sample.git
```

Expected result: A folder named _conduit-blog-backend-test-sample_ just has been downloaded.

2. Copy the file .env.example naming the new file .env
```sh
cp .env.example .env
```

3. Edit the .env and add the environment variables according to the user you created in the application.

4. Via terminal, open the just downloaded folder and run the tests:

```sh
gradle test
```

Expected result: BUILD SUCCESSFUL

5Open the test report located in: _build/reports/tests/test/index.html_
