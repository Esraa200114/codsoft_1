## Flashlight App 🔦
### An app that turns on the device's flashlight when the app is launched and turns it off when the app is closed.
## Output
https://github.com/Esraa200114/CODSOFT/assets/83236742/14d100af-38a0-48d8-a1fe-e9b99723f2fd
## Covered Rules
### 1. UI Design: The layout contains a single screen with a toggle button (flashlightSwitch) to control the flashlight. The Switch is provided for the user to turn the flashlight on and off.
### 2. Flashlight Functionality: The code checks for the availability of a camera flash on the device using getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH). If the device has a camera flash, it will use the CameraManager to turn on and off the flashlight based on the user's input (isChecked parameter).
### 3. App Lifecycle: The app handles the lifecycle events by overriding the onPause() and onDestroy() methods. When the app is paused or destroyed, the flashlight is turned off using flashLight(false) to ensure the flashlight is not left on when the app is closed.
### 4. Error Handling: The code implements error handling for scenarios where the device doesn't have a camera flash or if the flashlight fails to turn on. If the device doesn't have a camera flash, it displays a toast message: "No flashlight available on your device." If there's any CameraAccessException during the flashlight operation, it prints the stack trace to handle the exception.
