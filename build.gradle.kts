import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    `java-gradle-plugin`
    `maven-publish`
}

group = "org.tribot.wastedbro"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.lingala.zip4j:zip4j:2.6.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

gradlePlugin {
    plugins {
        create("TribotPlugin") {
            id = "org.tribot.wastedbro.tribot-gradle-plugin"
            implementationClass = "org.tribot.wastedbro.gradle.plugin.TribotPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitLab"
            url = uri("https://gitlab.com/api/v4/projects/22245399/packages/maven")
            credentials(HttpHeaderCredentials::class.java) {
                name = "Private-Token"
                value = "Wtj5U5yvMckswvCKHzQJ"
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }
}
