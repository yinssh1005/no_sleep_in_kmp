# No Sleep In KMP - Project Documentation for Claude AI

## Project Overview

**No Sleep In KMP** (不困模拟器) is a desktop application built with Kotlin Multiplatform and Compose Multiplatform that prevents computers from going to sleep by simulating mouse and keyboard activity.

**Version**: 1.0.2
**Main Language**: Kotlin (JVM Desktop)
**UI Framework**: Compose Multiplatform
**Package**: `y.e.no_sleep_in_kmp`

## Core Functionality

### Simulator (Main Feature)
Located in [App.kt](fleet-file://l4e07duhjibfpi1076g5/Users/ethan.yin/IdeaProjects/no-sleep-in-kmp/composeApp/src/jvmMain/kotlin/y/e/no_sleep_in_kmp/App.kt?type=file&root=%252F)

**Purpose**: Prevents system sleep by simulating user activity

**Mouse Simulation Modes** (`MouseMode` enum):
- `None` (不移动): No mouse movement
- `Horizontal` (左右移动): Left-right movement (±10 pixels)
- `Vertical` (上下移动): Up-down movement (±10 pixels)

**Keyboard Simulation Modes** (`KeyboardMode` enum):
- `None` (不输入): No keyboard input
- `Key9` (按数字9): Presses the '9' key
- `KeyCtrl` (按Ctrl键): Presses the Ctrl key

**Implementation Details**:
- Uses `java.awt.Robot` for mouse and keyboard simulation
- Executes actions every 1 second when running
- Mouse movements are reversed after 100ms to maintain cursor position
- Coroutine-based with `LaunchedEffect` for lifecycle management

## Architecture

### Project Structure
```
no-sleep-in-kmp/
├── composeApp/
│   ├── src/
│   │   ├── jvmMain/kotlin/y/e/no_sleep_in_kmp/
│   │   │   ├── main.kt              # Application entry point
│   │   │   ├── App.kt               # Main UI with simulator
│   │   │   └── Platform.kt          # Platform-specific code
│   │   └── jvmTest/
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
└── README.md
```

## Dependencies

### Key Libraries
- **Compose Multiplatform**: UI framework
- **Material3**: UI components
- **Coroutines**: Async operations
- **Lifecycle ViewModel & Runtime**: State management

### Build Configuration
- Target: JVM Desktop
- Main class: `y.e.no_sleep_in_kmp.MainKt`
- Package formats: DMG (macOS), MSI (Windows), DEB (Linux)

## Application Entry Point

[main.kt](fleet-file://l4e07duhjibfpi1076g5/Users/ethan.yin/IdeaProjects/no-sleep-in-kmp/composeApp/src/jvmMain/kotlin/y/e/no_sleep_in_kmp/main.kt?type=file&root=%252F)
- Window title: "不困模拟器"
- Window size: 450dp × 450dp (fixed, non-resizable)
- Icon: `drawable/icons8_sleep_96.png`

## Development Commands

### Running the Application
```bash
# macOS/Linux
./gradlew :composeApp:run

# Windows
.\gradlew.bat :composeApp:run
```

### Building Distribution Packages
```bash
# macOS DMG
./gradlew :composeApp:packageDmg

# Windows MSI (requires WiX Toolset)
.\gradlew.bat :composeApp:packageMsi

# Linux DEB
./gradlew :composeApp:packageDeb

# Create distributable without installer
./gradlew :composeApp:createDistributable
```

### Output Locations
- **MSI**: `composeApp/build/compose/binaries/main/msi/`
- **Distributable**: `composeApp/build/compose/binaries/main/app/`

## Important Notes for AI Assistance

### Code Conventions
1. **Language**: Chinese for UI strings (user-facing text)
2. **Package**: All code under `y.e.no_sleep_in_kmp`
3. **Compose**: Material3 components preferred
4. **State**: Use `remember`, `mutableStateOf`, and `LaunchedEffect`

### When Making Changes
1. **Read before Edit**: Always read files before modifying
2. **Preserve formatting**: Maintain existing indentation and style
3. **Testing**: Test with `./gradlew :composeApp:run` after changes
4. **Dependencies**: Check `composeApp/build.gradle.kts` for library versions

### Common Tasks
- **UI modifications**: Edit `App.kt`
- **New features**: Add to `App.kt` or create new Composable functions
- **Dependencies**: Add to `commonMain` or `jvmMain` sections in build.gradle.kts

### Platform-Specific Code
- **JVM only**: Desktop-specific code uses `java.awt.*` APIs
- **Robot API**: Requires platform permissions for mouse/keyboard simulation

## Git Status (Current)
- Branch: `main`
- Recent changes: Version 1.0.2 - Removed Notes feature, simplified to single-purpose app

## Recent Development History
1. **v1.0.2**: Removed Notes feature and Room database, simplified to single-purpose simulator
2. **v1.0.1**: Added resources, icons, Room database with Notes feature
3. **Initial**: Basic Kotlin Multiplatform setup with "No Sleep" simulator

## UI Structure

```
Window: "不困模拟器" (450x450, fixed)
└── App (MaterialTheme)
    └── SimulatorTab()
        └── Surface
            └── Column (centered, padding 16dp)
                ├── Text: "模拟器控制面板"
                ├── Row (horizontal, equal weight)
                │   ├── Column: Mouse Mode (RadioButtons)
                │   ├── Divider (vertical gray line)
                │   └── Column: Keyboard Mode (RadioButtons)
                └── Button: "开始运行" / "停止运行" (full width)
```

## Security & Permissions

- **Robot API**: May require accessibility permissions on macOS
- **Input Simulation**: Can control mouse/keyboard (use responsibly)

## Troubleshooting

### Common Issues
1. **Robot permissions**: Grant accessibility access on macOS
2. **MSI packaging**: Install WiX Toolset on Windows
3. **Build errors**: Run `./gradlew clean` and rebuild

### Debug Tips
- Use `./gradlew :composeApp:run` for immediate feedback
- Monitor coroutine lifecycle with logging in `LaunchedEffect`
- Check terminal output for any Robot initialization errors

## Future Enhancement Ideas
- Customizable simulation intervals
- System tray integration
- Statistics/usage tracking
- Hotkey support for quick start/stop
- Theme customization
- Configurable movement patterns

---

**Last Updated**: 2026-02-02
**Documentation Version**: 1.0.2
