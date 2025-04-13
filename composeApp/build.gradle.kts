import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    val apiKey = getApiKeyFromLocalProperties(project)
    val generatedSourcesDir = layout.buildDirectory.dir("generated/sources/apiKey/kotlin")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting

        val commonMain by getting {
            kotlin.srcDir(generatedSourcesDir)
        }

        tasks.register("generateApiKeyFile") {
            group = "build setup"
            description = "Generates ApiKeys.kt from local.properties"

            val outputDir = generatedSourcesDir.get().asFile
            val outputFile = File(outputDir, "com/yourcompany/yourproject/secrets/ApiKeys.kt")

            outputs.dir(outputDir)

            doLast {
                outputFile.parentFile.mkdirs()
                outputFile.writeText("""
                package org.marioonetti.firebasefundamentals.secrets

                /**
                 * Object containing API keys generated from local.properties.
                 * IMPORTANT: This file should be added to .gitignore
                 */
                object ApiKeys {
                    const val GEMINI_API_KEY: String = "$apiKey"
                }
            """.trimIndent())
                println("Generated API Key file at: ${outputFile.absolutePath}")
            }
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            dependsOn("generateApiKeyFile")
        }
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.contentnegotiation)

            implementation(libs.firebase.firestore)
            implementation(libs.firebase.auth)

            implementation(libs.android.nav.compose)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            implementation(libs.gemini.ai)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "org.marioonetti.firebasefundamentals"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.marioonetti.firebasefundamentals"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose.desktop {
    application {
        mainClass = "org.marioonetti.firebasefundamentals.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.marioonetti.firebasefundamentals"
            packageVersion = "1.0.0"
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)

    // Room dependencies
    add("kspAndroid", libs.room.compiler)
    add("kspDesktop", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
}

fun getApiKeyFromLocalProperties(project: Project): String {
    val properties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")
    var apiKey = ""

    if (localPropertiesFile.exists() && localPropertiesFile.isFile) {
        try {
            FileInputStream(localPropertiesFile).use { fis ->
                properties.load(fis)
                apiKey = properties.getProperty("GEMINI_API_KEY")
                    ?: run {
                        project.logger.warn("gemini api not found in local.properties. Using empty string.")
                        ""
                    }
            }
        } catch (e: Exception) {
            project.logger.error("Error reading local.properties", e)
        }
    } else {
        project.logger.warn("local.properties file not found at ${localPropertiesFile.absolutePath}. Using empty string for API Key.")
    }
    return apiKey.replace("\\", "\\\\").replace("\"", "\\\"")
}