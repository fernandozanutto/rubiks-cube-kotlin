group = "com.github.fernandozanutto"
version = "0.1"

plugins {
    id("java-library")
    id("maven-publish")
    kotlin("jvm") version "1.6.21"
}

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
