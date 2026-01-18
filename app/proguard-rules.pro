# Add project specific ProGuard rules here.
# You can control the set of absolute coordinates preserved with
# android.absoluteCoordiates.preserved

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Hilt
-dontwarn dagger.hilt.**

# Room
-keep class com.typerx.data.local.database.** { *; }
-keep class com.typerx.data.models.** { *; }

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class com.typerx.data.models.** { *; }

# RxJava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Kotlin
-keep class kotlin.reflect.jvm.internal.impl.load.kotlin.** { *; }
-dontwarn org.jetbrains.kotlin.**

# Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Accompanist
-keep class com.google.accompanist.** { *; }
-dontwarn com.google.accompanist.**