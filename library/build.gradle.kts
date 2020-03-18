@file:Suppress("UnstableApiUsage")

import com.novoda.gradle.release.PublishExtension
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm")
    id(Plugins.dokka) version Versions.dokka
}

apply(plugin = Plugins.ktlint)
apply(plugin = Plugins.ktlintIdea)

dependencies {
    implementation(Libs.kotlinStdlib)

    testImplementation(TestLibs.assertJCore)
    testImplementation(TestLibs.junit5Api)
    testRuntimeOnly(TestLibs.junit5Runtime)
    testImplementation(TestLibs.mockk)
}

val dokka by tasks.getting(DokkaTask::class) {
    outputDirectory = "$buildDir/docs/dokka"

    configuration {
        jdkVersion = 8

        includes = listOf("module.md")

        sourceLink {
            path = "./"
            url = "https://github.com/lordcodes/REPLACE_ME/blob/master/"
            lineSuffix = "#L"
        }
    }
}

apply(plugin = Plugins.bintrayRelease)

configure<PublishExtension> {
    bintrayUser = propertyOrEmpty("REPLACE_ME_Bintray_User")
    bintrayKey = propertyOrEmpty("REPLACE_ME_Bintray_ApiKey")

    userOrg = Library.BINTRAY_USER
    repoName = Library.BINTRAY_REPOSITORY

    groupId = Library.GROUP_ID
    artifactId = Library.ARTIFACT_ID
    publishVersion = Library.VERSION_NAME

    desc = Library.DESCRIPTION
    setLicences(Library.LICENSE)
    website = Library.WEBSITE
    issueTracker = Library.ISSUE_TRACKER
    repository = Library.SOURCE_CONTROL
}

if (project.isPublishing()) {
    apply(plugin = "maven")

    gradle.taskGraph.whenReady {
        tasks.withType<GenerateMavenPom>().configureEach {
            pom.description.set(Library.DESCRIPTION)
            pom.packaging = "jar"
            pom.url.set(Library.WEBSITE)

            pom.scm {
                url.set(Library.SOURCE_CONTROL)
                connection.set(Library.SOURCE_CONTROL)
                developerConnection.set(Library.SOURCE_CONTROL)
            }

            pom.licenses {
                license {
                    name.set("The Apache Software License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("repo")
                }
            }

            pom.developers {
                developer {
                    id.set(Library.DEVELOPER_USER)
                    name.set(Library.DEVELOPER_NAME)
                }
            }
        }
    }
}
