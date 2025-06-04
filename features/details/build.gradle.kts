plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	id("androidx.navigation.safeargs.kotlin") version "2.9.0" apply true
}

android {
	namespace = "com.example.details"
	compileSdk = 35

	defaultConfig {
		minSdk = 26

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
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
	buildFeatures {
		viewBinding = true
	}
}

dependencies {

	implementation(project(":component:navigation-contract"))
	implementation(project(":component:navigation"))

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(project.dependencies.platform(libs.koin.bom))
	implementation(libs.koin.core)
	implementation(libs.koin.android)
	implementation(libs.androidx.navigation.ui)
	implementation(libs.androidx.navigation.fragment)
}