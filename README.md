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
│       ├── test-pr.yml                 # Test pull requests
│       ├── test-changes.yml            # Test recent changes
│       └── release.yml                 # Create releases
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

## GitHub Actions Workflows

The project includes three GitHub Actions workflows:

### 1. Test Pull Request (`test-pr.yml`)
- Runs on pull requests to main/develop branches
- Executes unit tests
- Builds debug APK
- Runs lint checks
- Uploads test reports

### 2. Test Recent Changes (`test-changes.yml`)
- Runs on pushes to main/develop/feature branches
- Performs static analysis (ktlint, detekt)
- Runs unit tests
- Builds debug APK
- Runs connected Android tests on emulator
- Uploads APK artifact

### 3. Create Release (`release.yml`)
- Runs when tags are pushed (v*)
- Runs tests
- Builds release APK
- Signs the APK
- Creates GitHub release with changelog
- Uploads signed APK to release

## Running Workflows Locally

To run the workflows locally for testing purposes:

1. Install [Act](https://github.com/nektos/act) (GitHub Actions runner):
   ```bash
   # On Ubuntu/Debian
   sudo apt-get install act
   
   # On macOS
   brew install act
   
   # On Windows (using Chocolatey)
   choco install act-cli
   ```

2. Run workflows locally:
   ```bash
   # Run all workflows
   act
   
   # Run specific event
   act pull_request
   
   # Run specific job
   act -j test
   ```

## Dependencies

The project uses the following key dependencies:
- AndroidX Compose BOM
- Material 3 Components
- Hilt for Dependency Injection
- Room for local database
- DataStore for preferences
- Navigation Compose
- Lifecycle utilities for ViewModel
- Detekt for static analysis
- Ktlint for code formatting

## Development Best Practices

1. Follow the existing code style and conventions
2. Write unit tests for business logic
3. Use meaningful commit messages
4. Update the changelog for significant changes
5. Run static analysis before committing:
   ```bash
   ./gradlew ktlintCheck detekt
   ```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes
4. Run tests and static analysis
5. Commit your changes (`git commit -m 'Add amazing feature'`)
6. Push to the branch (`git push origin feature/amazing-feature`)
7. Open a Pull Request

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for a history of changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

If you encounter any issues or have questions, please file an issue in the GitHub repository.