plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
    id("signing")
    id("com.vanniktech.maven.publish") version "0.36.0"
}

android {
    namespace = "io.github.karthikeyan8296.flexlazyrow"
    compileSdk = 36

    buildFeatures {
        compose = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("io.coil-kt:coil-compose:2.6.0")
}

mavenPublishing {
    coordinates("io.github.karthikeyan8296", "flexlazyrow", "1.0.0")

    pom {
        name.set("FlexLazyRow")
        description.set("A Swiggy-style parallax LazyRow for Jetpack Compose")
        inceptionYear.set("2026")
        url.set("https://github.com/karthikeyan8296/flexlazyrow")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("karthikeyan8296")
                name.set("Karthikeyan")
                url.set("https://github.com/karthikeyan8296")
            }
        }
        scm {
            url.set("https://github.com/karthikeyan8296/flexlazyrow")
            connection.set("scm:git:git://github.com/karthikeyan8296/flexlazyrow.git")
            developerConnection.set("scm:git:ssh://git@github.com/karthikeyan8296/flexlazyrow.git")
        }
    }
}