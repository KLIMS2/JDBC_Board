plugins {
    id("java")
}

group = "com.ysj.java.board"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.assertj:assertj-core:3.11.1")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")

    implementation("mysql:mysql-connector-java:8.0.33")

    implementation("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}