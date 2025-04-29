import java.util.*

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

val isDummyBuild: String by project
val mcVersion: String by project
val modVersion: String by project
val modGroup: String by project
val modId: String by project
val modName: String by project
val modArchivesName: String by project
val modAuthor: String by project
val modIcon: String by project
val modDescription: String by project
val modCredits: String by project
version = modVersion

minecraft {
    version = "1.7.10-10.13.4.1614-1.7.10"
    runDir = "game"
    replace("@VERSION@", project.version)
    replace("BuildController.internalDummyBuildState()", isDummyBuild)
}

dependencies {
    implementation(":bukkit:1.7.10")
    implementation(":worldedit:6.1")
    implementation(":worldguard:6.1")
    implementation(":GriefPreventionPlus:13.3")
}

tasks {
    jar.configure {
        manifest {
            attributes(
                "Specification-Title" to modName,
                "Specification-Vendor" to modAuthor,
                "Specification-Version" to "1",
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
                "Implementation-Vendor" to modAuthor,
                "Implementation-Timestamp" to Date().toInstant().toString()
            )
        }

        doFirst {
            archiveClassifier = if (isDummyBuild.toBoolean()) {
                "dummy"
            } else {
                ""
            }
        }
    }


    processResources {
        from(sourceSets.main.get().resources) {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
            include("mcmod.info")
            expand(
                "isDummyBuild" to isDummyBuild,
                "mcVersion" to mcVersion,
                "modVersion" to modVersion,
                "modGroup" to modGroup,
                "modId" to modId,
                "modName" to modName,
                "modArchivesName" to modArchivesName,
                "modAuthor" to modAuthor,
                "modIcon" to modIcon,
                "modDescription" to modDescription,
                "modCredits" to modCredits,
            )
        }
    }

    register<Jar>("devJar") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveClassifier = if (isDummyBuild.toBoolean()) {
            "dummydev"
        } else {
            "dev"
        }

        from(sourceSets.main.get().output)
    }

    register<Jar>("sourcesJar") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveClassifier = "sources"
        from(sourceSets.main.get().allSource)
    }

    register<GradleBuild>("buildDummy") {
        this.group = "build"
        startParameter.projectProperties["isDummyBuild"] = "true"
    }

    register("buildAll") {
        this.group = "build"
        this.dependsOn("build")
        this.finalizedBy("buildDummy")
    }
}

artifacts {
    archives(tasks.named("devJar"))
    if (!isDummyBuild.toBoolean()) {
        archives(tasks.named("sourcesJar"))
    }
}