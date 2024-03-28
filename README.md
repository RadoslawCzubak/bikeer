# Bikeer - Your Ultimate Cycling Companion 🚴‍♂️

![kotlin-version](https://img.shields.io/badge/kotlin-1.9.22-blue)
> [!NOTE]  
> This project was created as part of the participation in the [100commitow.pl](https://100commitow.pl) competition.
**100commitow.pl** is a coding competition that encourages participants to make at least 100 commits to their GitHub repositories within 100 days, with a minimum of 1 commit per day.

## Overview

Bikeer is a mobile application designed to enhance your cycling experience by providing a comprehensive set of features to track, analyze, and optimize your rides. Whether you're a casual cyclist or a seasoned pro, Bikeer has something to offer for everyone.

## Key Features
🤖🔄 🍏🔄 **Speedometer**: the main functionality of the application. It is displayed on the main screen. Possible change of units **imperial and metric**. (In progress)

⬜️ **Map**: Displaying current position on the map, map browsing.

⬜️ **Ride Tracker**: This feature will calculate ride metrics using GPS data.
    - Ride Time
    - Route Taken
    - Distance Covered
    - Average Speed

⬜️ **GPX File Import**: Users can import their route in GPX format and view it on the map.

⬜️ **Cycling Activity Log**: Adding tracked activities with the ability to manually edit and add new records to keep an approximate record of cycling routes for maintenance purposes.

⬜️ **Bike Maintenance Log**: Capability to save service events related to the bike, including when, for how much, and what repairs or replacements were done. Additionally, the option 
to save detailed information.

⬜️ **User Authentication**: Users can create an account and log in for personalized experiences.

⬜️ **Firebase Synchronization**: Possible synchronization of data with the cloud for online data storage, ensuring independence from a specific device.

⬜️ **Route Saving**: Saving cycling routes for later review and analysis.

⬜️ **Bike Repair Shops**: The application will provide a list of bike repair stations in the area.

## Development

### 🛠️ Tech Stack

- Kotlin Multiplatform
- Kotlin Programming Language
- Kotlin Coroutines & Flow
- Jetpack Compose
- SwiftUI
- KMMViewModel
- Ktor Client
- SQL Delight
- Koin Dependency Injection Framework
- MVI pattern

### 🖼️ Native UI in KMM App

One of the powerful aspects of the Kotlin Multiplatform Mobile (KMM) platform is its ability to implement Native UI. This feature allows the KMM developers to utilize the advantages of native UI toolkits while sharing the core logic across iOS and Android.

This application employs Jetpack Compose for Android and SwiftUI for iOS. This design makes the app user-friendly for both iOS and Android users, as it allows them to understand the app's structure and all its unique features with ease.

Figma Design: https://www.figma.com/file/W6L57nfX5561QZ88If20JX/Bikeer?type=design&node-id=0%3A1&mode=design&t=SSzNChLucGpRtCm7-1
