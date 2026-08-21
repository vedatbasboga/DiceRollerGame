<<<<<<< HEAD
Simple Dice Roll Game
You can roll one dice or two dice 
=======
# Dice Roller Game

A simple and fun dice rolling app built with **Jetpack Compose** and **Material Design 3**. Roll one or two dice with smooth animations, sound effects, and haptic feedback.

## Features

- **Single Dice** - Roll one die with animated shake, rotate, and bounce effects
- **Two Dice** - Roll two dice simultaneously with synchronized animations
- **Sound Effects** - Satisfying dice roll sound on every throw
- **Haptic Feedback** - Vibration on button press for tactile response
- **Dark Mode** - Automatic light/dark theme based on system settings
- **Dynamic Colors** - Adapts to your device wallpaper colors (Android 12+)
- **Localization** - Available in English, Turkish, and Spanish

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose with Material3
- **Architecture:** MVVM (ViewModel + StateFlow)
- **Navigation:** Jetpack Navigation Compose (Single Activity)
- **Animation:** Compose Animation API (Animatable, spring, tween)
- **Min SDK:** 28 (Android 9)
- **Target SDK:** 37

## Project Structure

```
com.vedatbasboga.dicerollergame/
├── model/            # Data models (Dice)
├── viewmodel/        # ViewModels with UI state
├── ui/
│   ├── theme/        # Material3 theme (colors, typography)
│   ├── screen/       # Screens (Home, OneDice, TwoDice)
│   ├── component/    # Reusable composables (AnimatedDiceImage, RollButton)
│   └── navigation/   # NavGraph and route definitions
└── MainActivity.kt   # Single Activity entry point
```

## Build & Run

1. Clone the repository
2. Open in Android Studio (Quail 3 or later)
3. Sync Gradle and run on a device/emulator

## Download

<a href="https://play.google.com/store/apps/details?id=com.vedatbasboga.dicerollergame">
  <img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" width="200"/>
</a>

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
>>>>>>> 494331b (Add haptic feedback, README, LICENSE, and .gitignore for public release)
