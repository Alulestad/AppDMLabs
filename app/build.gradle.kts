plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")

    //firebase
    //id("com.android.application") ya esta implementado
    id("com.google.gms.google-services")

    //mio del trabajo en grupo
    id("kotlinx-serialization")

    //serializacion INge
    //id("kotlinx-serialization")


}

android {
    namespace = "com.examenp.recliclerview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.examenp.recliclerview"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures{
        viewBinding=true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.annotation:annotation:1.6.0")
    //implementation("com.google.firebase:firebase-firestore-ktx:24.10.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //image load implementation
    implementation ("io.coil-kt:coil:2.5.0")

    //Swipe recyclerview
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Material
    implementation("com.google.android.material:material:1.11.0")

    //Recyclerview
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    //coil es para agregar las imagenes
    implementation("io.coil-kt:coil:2.5.0")

    //corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    //Retrofit (API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Retrofit - Gson (converter)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    //navigation component
    val nav_version = "2.7.4"

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    //biometricos
    implementation("androidx.biometric:biometric:1.2.0-alpha05")

    //implementa el siclo de vida del view model
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.activity:activity:1.8.2")

    //firebase
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")

    //Firebase Authentication en Android
        // Import the BoM for the Firebase platform
    //implementation(platform("com.google.firebase:firebase-bom:32.3.1")) ya esta
        // Add the dependency for the Firebase Authentication library
        // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth-ktx")

    implementation("com.airbnb.android:lottie:3.4.0")

    //ktor

    val ktorVersion = "2.3.6"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    //inge , migracion retrofit a ktor
    //ktor Client Api service
    //implementation("io.ktor:ktor-client-core:$ktorVersion") ya está
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    // HTTP engine: The HTTP client used to perform network requests.
    //implementation("io.ktor:ktor-client-okhttp:$ktorVersion") //ya se repitio
    // Logging
    //implementation("io.ktor:ktor-client-logging:$ktorVersion") // ya se repitio
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0") //Segun la documentacion debo poner esto pero el inge como q no le puso, porque le puso luego
    // The serialization engine used to convert objects to and from JSON.
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    // content Negotiation
    //implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion") este ya se repitió
    // Json
    //implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion") este ya se repition
    //XML
    implementation("io.ktor:ktor-serialization-kotlinx-xml:$ktorVersion")
    //implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1") esto es  alo que me refiero


    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")


}