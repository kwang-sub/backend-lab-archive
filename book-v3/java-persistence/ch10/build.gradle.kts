plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Hibernate EntityManager (JPA를 위한 Hibernate 구현체)
    implementation("org.hibernate:hibernate-entitymanager:5.6.9.Final")

    // MySQL JDBC Connector
    runtimeOnly("mysql:mysql-connector-java:8.0.29")

    // Hibernate Validator (데이터 유효성 검사)
    implementation("org.hibernate:hibernate-validator:6.2.3.Final")
    // https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.26.3")

}

tasks.test {
    useJUnitPlatform()
}