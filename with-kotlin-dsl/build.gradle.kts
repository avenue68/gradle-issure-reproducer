plugins {
    kotlin("jvm") version "2.0.20"
    `kotlin-dsl`
    `java-library`
    `java-gradle-plugin`
    `maven-publish`
    groovy
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
    jacoco
    idea
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    testImplementation(platform("org.spockframework:spock-bom:2.4-M4-groovy-3.0"))
    testImplementation("org.spockframework:spock-core") {
        exclude(group = "org.codehaus.groovy")
    }
    testImplementation("com.athaydes:spock-reports:2.5.1-groovy-3.0") {
        exclude(group = "org.codehaus.groovy")
    }
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    plugins.invoke {
        create("documentConventionPlugin") {
            id = "com.example.example-plugin"
            implementationClass = "com.example.ExamplePlugin"
        }
    }
}
