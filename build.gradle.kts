import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

buildscript {
  repositories {
    jcenter()
  }
}

plugins {
  base
  kotlin("jvm") version (Versions.kotlin) apply false
  id(Plugins.gradleVersions) version (Versions.versionsPlugin)
  id(Plugins.ktlint) version (Versions.ktlintPlugin)
  id(Plugins.ktlintIdea) version (Versions.ktlintPlugin)
}

allprojects {
  repositories {
    jcenter()
  }

  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
  }

  tasks.withType<Test>().configureEach {
    reports {
      @Suppress("UnstableApiUsage")
      html.setEnabled(true)
    }
    testLogging {
      events("passed", "skipped", "failed")
    }
  }
}

subprojects {
  apply(plugin = Plugins.ktlint)
  apply(plugin = Plugins.ktlintIdea)

  ktlint {
    version.set(Versions.ktlint)
    reporters.set(setOf(ReporterType.PLAIN, ReporterType.CHECKSTYLE))
    filter {
      include("**/src/**/kotlin/**")
    }
  }
}
