# Test Assignment for the position of Strong Junior Java Developer at BotsCrew

## Steps to run:
1) Clone the repository
2) Run the command `docker run -p 5432:5432 -d --name uni-bot -e POSTGRES_USER=uni-bot -e POSTGRES_PASSWORD=uni-bot -e POSTGRES_DB=uni-bot postgres` (Also available in `docs/database.md`)
3) Choose profile (`dev` or `prod`). I recommend to use dev profile, because it fills database with predefined data, which makes it easier to test the application. (Predefined data is available in `fakedata/DataPreInitializationService.java`)
4) Run the Spring Boot application 
5) Enjoy!

## Information about implementation:
1) SQL scripts are available in `src/main/resources/db/migration`
2) Predefined data is available in `fakedata/DataPreInitializationService.java`
3) To simplify creation of duplicate data, I use concept called [ObjectMother by Martin Fowler](https://martinfowler.com/bliki/ObjectMother.html). They're available in `fakedata` package.
4) Tests are available in `src/test/java/com/panilya/botscrewtesttask`. For assertions I use [AssertJ](https://joel-costigliola.github.io/assertj/), for tests that need to interact with DB - [Testcontainers](https://www.testcontainers.org/).