plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
}

version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop")
    ios()
    iosSimulatorArm64()

    js(IR) {
        browser()
    }

    macosX64 {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    macosArm64 {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    cocoapods {
        summary = "Shared code for SunAndStorm-KMP"
        homepage = "https://github.com/ShushantTiwari-ashu/SunAndStorm-KMP"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] =
            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.core)
                implementation(libs.ktor.json)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.content.negotiation)
                implementation(compose.runtime)
                api(compose.foundation)
                api(compose.animation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(libs.compose.resource)
                implementation(libs.kotlinx.serialization.json)
                api(libs.imageloader)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
                implementation(libs.kotlinx.serialization.json)
                api(libs.precompose.moe)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.material)
                implementation(libs.accompanist.systemcontroller)
            }
        }
        val iosMain by getting
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.coroutines.swing)
                implementation(libs.ktor.client.cio)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.js)
                implementation(libs.ktor.client.json)
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }

        val macosMain by creating {
            dependsOn(commonMain)
        }
        val macosX64Main by getting {
            dependsOn(macosMain)
        }
        val macosArm64Main by getting {
            dependsOn(macosMain)
        }

        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].resources.srcDir("src/commonMain/resources")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

kotlin {
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.all {
            freeCompilerArgs = freeCompilerArgs + "-Xdisable-phases=VerifyBitcode"
            freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
        }
    }
}