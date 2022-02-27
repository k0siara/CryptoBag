import com.patrykkosieradzki.cryptobag.buildsrc.Libs

plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":utils"))

    implementation("com.google.dagger:hilt-android:2.37")
    kapt("com.google.dagger:hilt-android-compiler:2.37")

    testImplementation("junit:junit:4.13.2")

    // Network
    implementation(Libs.SquareUp.retrofit)
    implementation(Libs.SquareUp.moshiKotlin)
    implementation(Libs.SquareUp.retrofitMoshiConverter)
    implementation(Libs.SquareUp.moshiAdapters)
    implementation(Libs.SquareUp.loggingInterceptor)
}