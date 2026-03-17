# NMIT Scanner

A mobile application for managing and storing PDF documents on Firebase. Users can upload PDF files, view previously uploaded documents, and access them anytime through an intuitive interface.

## Features

- **PDF Selection**: Browse and select PDF files from your device
- **Firebase Upload**: Upload PDF files securely to Firebase Storage
- **Document Management**: View all uploaded PDFs in a organized list
- **PDF Viewer**: Built-in PDF viewer to read documents directly in the app
- **Real-time Database**: Integration with Firebase Realtime Database for tracking uploaded files

## Technical Stack

### Languages & Frameworks
- **Kotlin**: Primary programming language
- **Android SDK**: Target API 34, Minimum API 24
- **Jetpack Components**: AndroidX libraries for modern Android development

### Backend & Storage
- **Firebase Realtime Database**: Store metadata for uploaded PDFs
- **Firebase Cloud Storage**: Store actual PDF files
- **Firebase Authentication**: Secure access to backend services

### UI & Architecture
- **Material Design**: Material UI components for modern interface
- **View Binding**: Type-safe view references
- **RecyclerView Adapter**: Display lists of PDF documents efficiently

### Libraries
- AndroidX Core, AppCompat, ConstraintLayout
- Material Design Components
- Lifecycle Runtime
- PDF Viewer Library: `pdf-viewer-android` (v2.0)

## Project Structure

```
NMITScanner2/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/nmitscanner/
│   │       │   ├── MainActivity.kt           # Main activity for file selection & upload
│   │       │   ├── AllPdfsActivity.kt        # Activity to view all uploaded PDFs
│   │       │   ├── PdfViewerActivity.kt      # Activity to view individual PDF
│   │       │   ├── PdfFile.kt                # Data model for PDF files
│   │       │   └── PdfFilesAdapter.kt        # Adapter for displaying PDF list
│   │       ├── res/                          # Resources (layouts, strings, drawable)
│   │       └── AndroidManifest.xml           # App manifest
│   └── build.gradle.kts                      # App-level dependencies
├── gradle/                                   # Gradle configurations
├── build.gradle.kts                          # Root-level build configuration
└── settings.gradle.kts                       # Gradle settings
```

## Getting Started

### Prerequisites
- Android Studio (latest version recommended)
- JDK 8 or higher
- Android SDK API 34 or higher (for compilation)
- Firebase account and project setup

### Setup Instructions

1. **Clone or open the project** in Android Studio

2. **Configure Firebase**:
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com)
   - Download the `google-services.json` file
   - Place it in the `app/` directory

3. **Build the project**:
   ```bash
   ./gradlew build
   ```

4. **Install and run**:
   - Connect an Android device (API 24+) or emulator
   - Run the app via Android Studio or:
   ```bash
   ./gradlew installDebug
   ```

## Usage

### Upload a PDF
1. Open the app
2. Tap the "Select PDF" button
3. Choose a PDF file from your device
4. The filename will be displayed
5. Tap "Upload" to upload to Firebase

### View Uploaded PDFs
1. Tap the "Show All" button to navigate to the All PDFs screen
2. Browse through the list of uploaded documents
3. Tap any PDF to open it in the built-in viewer

### View PDF Details
- The PDF viewer displays the full document content
- Supports standard PDF navigation and viewing features

## Dependencies

Key dependencies managed in `gradle/libs.versions.toml`:
- Firebase Realtime Database
- Firebase Cloud Storage
- AndroidX Lifecycle
- Material Design 3
- PDF Viewer Library

## Permissions

The app requires the following permission:
- `READ_EXTERNAL_STORAGE`: To access PDF files on the device

## Build & Compile

- **Min SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Compile SDK**: API 34
- **Java Compatibility**: Version 1.8

## Version Info

- **App Version**: 1.0
- **Version Code**: 1
- **Application ID**: com.example.nmitscanner

## Contributing

To extend or modify this project:
1. Follow Kotlin coding conventions
2. Use AndroidX libraries for new components
3. Test on multiple API levels (24+)
4. Update this README with new features

## Future Enhancements

- Implement user authentication
- Add search and filter functionality
- Support for other document formats
- Offline access to downloaded PDFs
- Cloud sync across devices
- Document sharing capabilities

## License

This project is proprietary and intended for educational purposes.

## Support

For issues or questions, please refer to the project structure and source code comments.

---

**Built for NMIT** | Android PDF Management Solution
