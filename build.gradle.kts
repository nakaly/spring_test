/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * User Manual available at https://docs.gradle.org/5.4.1/userguide/tutorial_java_projects.html
 */

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.4.RELEASE")
    }
}

plugins {
    // Apply the java plugin to add support for Java
    java
    eclipse
    idea
}

apply {
    plugin("java")
    plugin("eclipse")
    plugin("idea")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
}

val sourceCompatibility = "1.11"
val targetCompatibility = "1.11"

repositories {
    // Use mavenCentral for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()
}

dependencies {
    // This dependency is found on compile classpath of this component and consumers.
    compile("org.springframework.boot:spring-boot-starter-web")

    // Use JUnit test framework
    testCompile("org.springframework.boot:spring-boot-starter-test")
}
