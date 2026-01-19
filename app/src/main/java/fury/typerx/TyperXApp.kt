package fury.typerx

import android.app.Application
import timber.log.Timber
import fury.typerx.BuildConfig

class TyperXApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize logging in debug mode
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}