# TyperX - Advanced Typing Tutor Application

TyperX is a comprehensive typing tutor application built for Android with Kotlin, featuring Material You design principles and support for both English and Hindi (Krutidev 10) typing practice.

## Features

### Language Support
- English typing with standard QWERTY layout
- Hindi typing using Krutidev 10 layout with embedded font
- Seamless switching between languages

### Typing Modes
- Predefined time modes: 15, 30, 60, 120, 300, 600, 900 seconds
- Custom duration mode
- Custom text practice mode

### Practice Categories
- Lowercase only
- Lowercase + uppercase
- Numbers only
- Numbers + text (English)
- Mixed mode (Hindi + English)
- Word, sentence, and paragraph practice modes

### Typing Engine
- Real-time WPM calculation
- Accuracy percentage tracking
- Error highlighting
- Backspace handling options

### Theme System
- Light theme
- Dark theme
- Catppuccin palette variants (Latte, Frappe, Macchiato, Mocha)
- Dracula theme
- Material You dynamic theming based on wallpaper

### Database & Storage
- SQLite Room database for storing typing history
- Local storage for preferences and settings
- Offline-first design

### Additional Features
- Local leaderboard
- History of attempts with detailed statistics
- Daily practice goal tracking
- Font size adjustment
- Sound and haptic feedback
- Comprehensive result analysis

## GitHub Actions Workflows

The project includes three required GitHub Actions workflows:

### 1. Pull Request Check (`pr-check.yml`)
- Runs automatically on pull requests to main branch
- Manually triggerable with workflow_dispatch
- Performs lint checks
- Runs unit tests
- Builds debug APK with Android 11+ minimum SDK
- Uploads debug APK as artifact

### 2. Test Commits (`test-commits.yml`)
- Manual workflow (workflow_dispatch)
- Allows testing on any specified branch
- Builds release APK with Android 11+ minimum SDK
- Runs unit tests
- Uploads tested release APK as artifact

### 3. Make Release (`release.yml`)
- Manual workflow (workflow_dispatch)
- Requires version input
- Uses signing secrets to build signed APK
- Creates GitHub release with APK attached
- Enforces Android 11+ minimum SDK

## Architecture

The application follows a clean architecture pattern with MVVM:

```
TyperX
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/typerx
│   │   │   │   ├── TyperXApp.kt              # Application class with Hilt
│   │   │   │   ├── MainActivity.kt           # Main activity
│   │   │   │   ├── di/                     # Dependency injection modules
│   │   │   │   │   └── DatabaseModule.kt
│   │   │   │   ├── ui/
│   │   │   │   │   ├── screens/
│   │   │   │   │   │   ├── HomeScreen.kt
│   │   │   │   │   │   ├── TypingScreen.kt
│   │   │   │   │   │   ├── TypingViewModel.kt
│   │   │   │   │   │   ├── HistoryScreen.kt
│   │   │   │   │   │   ├── HistoryViewModel.kt
│   │   │   │   │   │   ├── SettingsScreen.kt
│   │   │   │   │   │   └── ResultScreen.kt
│   │   │   │   │   ├── theme/
│   │   │   │   │   │   ├── Theme.kt
│   │   │   │   │   │   ├── Color.kt
│   │   │   │   │   │   ├── Type.kt
│   │   │   │   │   │   ├── DarkTheme.kt
│   │   │   │   │   │   └── CatppuccinTheme.kt
│   │   │   │   │   └── components/
│   │   │   │   │       ├── TimerView.kt
│   │   │   │   │       ├── TextInputView.kt
│   │   │   │   │       └── StatsCard.kt
│   │   │   │   ├── engine/
│   │   │   │   │   ├── TypingEngine.kt
│   │   │   │   │   ├── TypingSession.kt
│   │   │   │   │   ├── WpmCalculator.kt
│   │   │   │   │   └── AccuracyTracker.kt
│   │   │   │   ├── keyboard/
│   │   │   │   │   ├── PhysicalKeyboard.kt
│   │   │   │   │   ├── SoftKeyboardManager.kt
│   │   │   │   │   └── KrutidevLayout.kt
│   │   │   │   ├── data/
│   │   │   │   │   ├── models/
│   │   │   │   │   │   ├── TypingResult.kt
│   │   │   │   │   │   └── PracticeMode.kt
│   │   │   │   │   ├── local/
│   │   │   │   │   │   ├── database/
│   │   │   │   │   │   │   ├── TypingDatabase.kt
│   │   │   │   │   │   │   └── TypingResultDao.kt
│   │   │   │   │   │   ├── converters/
│   │   │   │   │   │   │   └── DateConverter.kt
│   │   │   │   │   │   └── repository/
│   │   │   │   │   │       └── TypingResultRepositoryImpl.kt
│   │   │   │   │   └── repository/
│   │   │   │   │       └── TypingResultRepository.kt
│   │   │   │   └── utils/
│   │   │   │       ├── FeedbackManager.kt
│   │   │   │       ├── PreferencesManager.kt
│   │   │   │       ├── DailyGoalManager.kt
│   │   │   │       └── SampleTextProvider.kt
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── mipmap/
│   │   │   │   ├── values/
│   │   │   │   ├── fonts/                  # Krutidev10 font
│   │   │   │   └── xml/
│   │   │   └── AndroidManifest.xml
│   └── build.gradle
├── .github/
│   └── workflows/
│       ├── pr-check.yml                   # PR validation workflow
│       ├── test-commits.yml               # Manual commit testing
│       └── release.yml                    # Release creation workflow
└── gradle/
    └── wrapper/
```

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Repository Pattern
- **Dependency Injection**: Hilt
- **Database**: Room with SQLite
- **Preferences**: DataStore
- **Navigation**: Navigation Compose

## Database Schema

The application uses Room to store typing results:

```sql
CREATE TABLE typing_sessions (
    id TEXT PRIMARY KEY NOT NULL,
    text TEXT NOT NULL,
    language TEXT NOT NULL,
    mode TEXT NOT NULL,
    duration INTEGER NOT NULL,
    wpm REAL NOT NULL,
    accuracy REAL NOT NULL,
    errors INTEGER NOT NULL,
    totalKeystrokes INTEGER NOT NULL,
    correctKeystrokes INTEGER NOT NULL,
    date INTEGER NOT NULL,
    elapsedTime INTEGER NOT NULL
);
```

## Setup Instructions

1. Clone the repository
2. Open in Android Studio
3. Sync the project with Gradle files
4. Build and run on an Android device or emulator

## GitHub Actions Secrets Required

For the release workflow to work, you need to set up these secrets in your repository:
- `SIGNING_KEY` - Base64 encoded keystore file
- `ALIAS` - Keystore alias
- `KEY_STORE_PASSWORD` - Keystore password
- `KEY_PASSWORD` - Key password

## Dependencies

The project uses the following key dependencies:
- AndroidX Compose BOM
- Material 3 Components
- Hilt for Dependency Injection
- Room for local database
- DataStore for preferences
- Navigation Compose
- Lifecycle utilities for ViewModel

## Contributing

Feel free to submit issues and enhancement requests. Pull requests are welcome!

## License

This project is licensed under the MIT License.