# NammaHomeStay

A simple Android application for managing and exploring homestays.
Built using Kotlin, Firebase Authentication, and Cloud Firestore in Android Studio.

---

## Features

- User Login & Registration
- Dashboard Screen
- Add Homestay Details
- View Homestay Listings
- Firebase Authentication
- Firestore Database Integration
- Simple and Clean UI
- Dark Theme Design

---

## Tech Stack

- Kotlin
- Android Studio
- Firebase Authentication
- Cloud Firestore
- XML Layouts
- RecyclerView

---

## Project Structure

app/
 ├── auth/
 ├── home/
 ├── profile/
 ├── adapter/
 ├── model/
 └── theme/

## Firebase Setup
1. Go to the Firebase Console  
   https://console.firebase.google.com/
2. Create a new project named:
   NammaHomeStay
   3. Add an Android app with package name:
   com.example.internship
4. Download the `google-services.json` file
5. Place the file inside:
   app/google-services.json
6. In Firebase Console, enable:
- Email/Password Authentication
- Cloud Firestore Database
7. Sync the project with Gradle


## How to Run
1. Clone the repository
```bash
git clone https://github.com/arshiyaazmi/NammaHomeStay.git
```
2. Open project in Android Studio
3. Connect an Android device or start an emulator
4. Click the **Run ▶** button
5. Register/Login and start adding homestays
