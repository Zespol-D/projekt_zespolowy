plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.projekt_zespolowy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.projekt_zespolowy"
        minSdk = 31
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.tv:tv-material:1.0.0-alpha10")
    implementation ("com.microsoft.sqlserver:mssql-jdbc:12.4.2.jre11")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")

    annotationProcessor("androidx.room:room-compiler:2.6.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation ("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.compose.material:material:1.5.4")
    implementation("com.google.android.engage:engage-core:1.3.1")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.5")
    implementation ("androidx.compose.compiler:compiler:1.5.4")
    implementation ("androidx.compose.foundation:foundation:1.5.4")
    implementation ("androidx.compose.material:material:1.5.4")
    implementation ("androidx.compose.ui:ui-tooling:1.5.4")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}