plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
    maven("https://repo.kotlin.link")
}

dependencies {
    api("space.kscience:kmath-core:0.3.0-dev-14")
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}
