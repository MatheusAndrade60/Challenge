plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("plugin.serialization") version "1.5.31"
    kotlin("kapt")
}

android {
    namespace = "br.com.fiap.challenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.fiap.challenge"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom)) // Usar o BOM para Compose
        implementation(libs.androidx.ui) // Inclui 'ui', 'ui.graphics' e 'ui.tooling.preview'
        implementation(libs.androidx.material3)
        implementation(libs.androidx.navigation.compose)
        implementation(libs.androidx.room.common)
        implementation(libs.androidx.room.ktx)

        // Remova as declarações manuais de versões do Compose:
        // implementation ("androidx.compose.ui:ui:1.0.5")
        // implementation ("androidx.compose.material:material:1.0.5")
        // implementation ("androidx.compose.ui:ui-tooling-preview:1.0.5")

        // Manter o BOM atualizado:
        implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
        implementation("androidx.core:core-ktx:1.8.0")

        // Testes
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom)) // Gerenciamento do Compose
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }



    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
}
