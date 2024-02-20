// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    id("androidx.navigation.safeargs.kotlin") version "2.7.1" apply false
    //se usa false porque quiere decir que esta latente pero no lo aplica
    // si quisiera programar para kotlin y java solo seria androidx.navigation.safeargs


    //firebase
    id("com.google.gms.google-services") version "4.4.0" apply false

    //lo mio
    //kotlin("plugin.serialization") version "1.8.10" apply false


    //Inge
    //id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22" apply false NO VALE es el sigueinte:
    kotlin("plugin.serialization") version "1.9.22" apply false
}