# Bard4j

[![build](https://github.com/llmjava/bard4j/actions/workflows/main.yml/badge.svg)](https://github.com/llmjava/bard4j/actions/workflows/main.yml) [![Jitpack](https://jitpack.io/v/llmjava/bard4j.svg)](https://jitpack.io/#llmjava/bard4j) [![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

</b>

A **Java** and **Retrofit2** based **Client Library** that you can use to interact with **Google Bard**. It can be used in Android or any Java and Kotlin Project.

## Add Dependency

### Gradle

To use library in your gradle project follow the steps below:

1. Add this in your root `build.gradle` at the end of repositories:
    ```groovy
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    ```
2. Add the dependency
   ```groovy
   dependencies {
       def BARD4J_VERSION = "..."
       implementation "com.github.llmjava:bard4j:$BARD4J_VERSION"
   }
   ```

### Maven

To use the library in your Maven project, follow the steps below:

1. Add the JitPack repository to your build file:
    ```xml
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```
2. Add the dependency
    ```xml
    <dependency>
        <groupId>com.github.llmjava</groupId>
        <artifactId>bard4j</artifactId>
        <version>${BARD4J_VERSION}</version>
    </dependency>
    ```

## Usage

Example code to use the **Bard API**:

Create a configuration file
```properties
# Set API key using env variable or put actual value
bard.apiKey=${env:BARD_API_KEY}
```

Create an instance of `BardClient` and submit text generation requests

```java
import com.github.llmjava.bard.*;

public class Main {
   public static void main(String[] args) {
       BardConfig config = BardConfig.fromProperties("bard4j.properties");
       BardClient client = new BardClient.Builder().withConfig(config).build();

       String input = "tell me a joke";

       System.out.println(client.generate(input));
   }
}

```

## Build Project

Clone the repository and import as Maven project in IntelliJ IDEA or Eclipse

Before building the project, make sure you have the following things installed.

- Maven
- Java 8

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To build the library using Gradle, execute the following command

```shell
./gradlew build
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.