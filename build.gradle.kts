import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.60"
}

group = "de.eckerta.kata"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.7")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.7")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.3.41")
    testImplementation("org.amshove.kluent:kluent:1.55")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}