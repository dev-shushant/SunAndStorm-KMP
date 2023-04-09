import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    //id("org.openjfx.javafxplugin")
}

/*javafx {
    version = "16"
    modules = arrayOf(
        "javafx.controls",
        //"javafx.fxml",
        "javafx.graphics",
        //"javafx.web",
        "javafx.media",
        "javafx.swing",
        //"javafx.swt",
        "javafx.base"
    ).toMutableList()
}*/

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "shushant.sun_and_storm_kmp.main"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "SunAndStorm-App"
            packageVersion = "1.0.0"

            windows {
                menuGroup = "SunAndStorm App KMP"
                upgradeUuid = "SunAndStorm-App-KMP"
            }
        }
    }
}

