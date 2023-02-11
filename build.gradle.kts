plugins {
    `maven-publish`

    id("io.freefair.lombok") version "6.6.1"
    id("java")
}

group = "space.maxus"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    repositories {
        maven("https://repo.repsy.io/mvn/maxuss/artifacts") {
            credentials(PasswordCredentials::class)
        }

        publications {
            create<MavenPublication>(project.name) {
                artifact(tasks.named("javadocJar"))
                artifact(tasks.named("sourcesJar"))

                this.groupId = project.group.toString()
                this.artifactId = project.name.toLowerCase()
                this.version = project.version.toString()

                pom {
                    name.set(project.name)
                    description.set(project.description)

                    developers {
                        developer {
                            name.set("Maxuss")
                        }
                    }

                    licenses {
                        license {
                            name.set("The MIT License (MIT)")
                            url.set("https://mit-license.org")
                        }
                    }

                    url.set("https://github.com/Maxuss")

                    scm {
                        connection.set("scm:git:git://github.com/Maxuss/CDLib.git")
                        url.set("https://github.com/Maxuss/CDLib/tree/master")
                    }
                }
            }
        }
    }
}