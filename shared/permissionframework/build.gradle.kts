plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

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
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "permissionframework"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.multiplatform.settings)
                implementation(libs.multiplatform.settings.coroutines)
                implementation(libs.kermit)
                api(libs.koin.core)
                implementation(compose.runtime)
                api(project(":shared:models"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting

        val macosMain by creating {
            dependsOn(commonMain)
        }
        val macosX64Main by getting {
            dependsOn(macosMain)
        }
        val macosArm64Main by getting {
            dependsOn(macosMain)
        }
        val jsMain by getting{
            dependsOn(commonMain)
        }
        val desktopMain by getting
    }
}

android {
    namespace = "dev.shushant.permissionframework"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}