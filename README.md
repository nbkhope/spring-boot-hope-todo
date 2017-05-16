# Hope Todo

## Setting up MySQL database

Install MySQL. For MAC OS X, using, Brew:

```sh
brew install mysql
```

Then, login using the database client:

```sh
mysql -uroot
```

Run the following commands:
```sql
CREATE DATABASE hopetodo;
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'abc123';
GRANT ALL ON hopetodo.* TO 'springuser'@'localhost';
```

Note the database password will be `abc123`. After that, make sure to edit `src/main/resources/application.properties` to have the following setting for `ddl-auto`:

```
spring.jpa.hibernate.ddl-auto=create
```

## Running the Web App

Use the provided gradle wrapper to build and run the app.

To build the app:

```sh
./gradlew build
```

To run the app using Spring Bootrun:

```sh
./gradlew bootRun
```

Alternatively, you can also run the app using (don't forget to build beforehand!):

```sh
java -jar build/libs/hope-todo-0.1.0.jar
```

## Securing the Database

After running the app for the first time, the database tables will be created. After that, you can take steps to better secure the database. Login to mysql and run the following commands:

```sql
REVOKE ALL ON hopetodo.* FROM 'springuser'@'localhost';
GRANT SELECT, INSERT, DELETE, UPDATE ON hopetodo.* TO 'springuser'@'localhost';
```

Finally, update the `ddl-auto` setting in `src/main/resources/application.properties` to none:

```
spring.jpa.hibernate.ddl-auto=none
```