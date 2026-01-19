# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles property in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# Keep the R.styleable and similar resource constants
-keepclassmembers public class **.R$* {
    public static <fields>;
}

# Keep the classes that are used for reflection
-keep class androidx.lifecycle.** { *; }
-keep class androidx.compose.** { *; }
-keep class androidx.navigation.** { *; }
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep Room database classes
-keep class * extends androidx.room.Entity
-keep class * extends androidx.room.Dao
-keep class * extends androidx.room.Database

# Keep Compose internal classes if they're being stripped
-dontwarn androidx.compose.**

# Speed up builds by skipping some optimizations during development
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*

# Skip preverification for faster builds
-dontpreverify

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }
-keep class kotlin.jvm.** { *; }
-keep class kotlin.collections.** { *; }

# Keep Timber logging
-keep class timber.log.** { *; }
-dontwarn timber.log.**