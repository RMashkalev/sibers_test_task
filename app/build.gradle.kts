plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	id("androidx.navigation.safeargs.kotlin") version "2.9.0" apply true
}

android {
	namespace = "com.example.sibers_test_task"
	compileSdk = 35

	defaultConfig {
		applicationId = "com.example.sibers_test_task"
		minSdk = 26
		targetSdk = 35
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

	implementation(project(":features:feed"))
	implementation(project(":features:details"))
	implementation(project(":component:network"))
	implementation(project(":component:navigation"))

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	implementation(project.dependencies.platform(libs.koin.bom))
	implementation(libs.koin.core)
	implementation(libs.koin.android)
	implementation(libs.androidx.navigation.ui)
	implementation(libs.androidx.navigation.fragment)
}