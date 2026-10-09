# Marin Mascot Prototype

A JavaFX desktop mascot prototype featuring Marin Kitagawa.  
The mascot sits transparently on the desktop, can be dragged around the screen,
and changes its displayed image based on the current mascot state.

---

## Prerequisites

| Requirement | Version |
|-------------|---------|
| Java (JDK)  | 23      |
| Maven       | 3.6+    |

> JavaFX **does not** need to be installed separately.  
> Maven downloads it automatically from Maven Central.

---

## Build

```bash
mvn clean compile
```

## Test

```bash
mvn clean test
```

## Run

```bash
mvn javafx:run
```

---

## Currently Implemented Features

- Transparent JavaFX window with no window decorations
- Mascot image displayed using `ImageView`
- Six mascot states: `IDLE`, `HAPPY`, `TALKING`, `THINKING`, `SLEEPING`, `STUDYING`
- Click the mascot to cycle to a random new state (excludes `THINKING` and the current state)
- Drag the mascot anywhere on the screen
- Screen bounds enforcement — the window cannot be dragged outside the visible screen
- Console-based state switching via `app.ConsoleTest` (run separately from the JavaFX thread)
- `Platform.runLater()` used for all JavaFX UI updates triggered from the console thread

---

## Current Limitations

- Console test thread is commented out in `app.DisplayOnScreen` (can be re-enabled manually)
- No animations between state transitions
- No speech bubbles or menu
- No AI or database functionality
- Single-monitor support only (uses primary screen bounds)

---

## Project Structure

```
EmotionStates/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── app.DisplayOnScreen.java    # JavaFX Application entry point
    │   │   ├── mascot.MascotState.java        # Enum of mascot states
    │   │   ├── mascot.MascotRenderer.java     # Maps mascot.MascotState -> resource path
    │   │   ├── mascot.MascotSwitch.java       # Holds current state and loads Image
    │   │   ├── mascot.MascotDragHandler.java  # Mouse drag + screen bounds logic
    │   │   └── app.ConsoleTest.java        # Console-based state switching
    │   └── resources/
    │       └── state_images/           # Mascot PNG images (one per state)
    └── test/
        └── java/                       # JUnit 5 tests (future)
```
