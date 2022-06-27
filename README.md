[![Android CI](https://github.com/renetik/renetik-android-json/workflows/Android%20CI/badge.svg)](https://github.com/renetik/renetik-android-json/actions/workflows/android.yml)
# Renetik Android Json
Framework to enjoy, improve and speed up your application development while writing readable code.
Used as library for music production and performance app Renetik Instruments www.renetik.com as well as other projects.

```gradle
allprojects {
    repositories {
        // For master-SNAPSHOT
        maven { url 'https://github.com/renetik/maven-snapshot/raw/master/repository' }
        // For release builds
        maven { url 'https://github.com/renetik/maven/raw/master/repository' }
    }
}
```
Step 2. Add the dependency
```gradle
dependencies {
    implementation 'com.renetik.library:renetik-android-json:$latest-renetik-android-release'
}
```

## [Html Documentation](https://renetik.github.io/renetik-android-json/)
