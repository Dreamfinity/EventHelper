plugins {
    kotlin("jvm") version "2.1.20" apply true
    id("java")
    id("forge") version "1.2-1.1.+"
}

repositories {
    flatDir {
        dirs("lib")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

minecraft {
    version = "1.7.10-10.13.4.1614-1.7.10"
    runDir = "game"
}

dependencies {}


tasks.register("devJar", Jar::class) {}

tasks.register("sourcesJar", Jar::class) {
    archiveClassifier = "sources"
}

tasks.register("buildDummy", GradleBuild::class) {}

tasks.register("buildAll") {}


artifacts {}