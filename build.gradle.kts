plugins {
    java
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.panilya"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

buildscript {
    repositories {
        maven(url = "https://repo1.maven.org/maven2")
    }
}

repositories {
    mavenCentral()
}

extra["testcontainersVersion"] = "1.18.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")

    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("net.datafaker:datafaker:1.9.0")

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.testcontainers:junit-jupiter:1.18.0")
    implementation("org.testcontainers:testcontainers:1.18.0")
    implementation("org.testcontainers:postgresql:1.18.0")

    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
