Kukulbank 📱💳

A modern, secure banking app that allows users to register, log in, and view their bank information, including balances and transaction details. Built with Jetpack Compose and Firebase.
🚀 Challenge Overview

The goal of Kukulbank was to create a functional banking application with essential user management and banking features.
Key Tasks:

    User Registration:
        Collect personal data: Name, surname, email, and password.
        Capture an ID photo.
        Display a success screen after registration.

    User Login:
        Login screen with email and password fields.
        Button to access the registration process (Onboarding).
        Redirect to the Home screen upon successful login.

    Bank Information (Home Screen):
        Display user’s balance and list of transactions.
        Provide transaction details on click of a movement item.

    Database:
        Store user data and transactions in Firebase.
        Prepopulate Firebase with test users and movements for easy login testing.

🛠️ Technologies Used

    Jetpack Compose: UI toolkit for building the user interface.
    Compose Navigation: For managing navigation between screens.
    Firebase Realtime Database: Backend to store user and transaction data.
    Firebase Authentication (Email & Password): Secure user authentication.
    MVVM Architecture: Organized separation between UI and business logic.
    Hilt: Dependency injection for easier testing and modularization.
    Kotlin Coroutines and Flow: For asynchronous operations.

🏗️ Architecture

This project follows the MVVM (Model-View-ViewModel) architecture pattern, ensuring better separation of concerns and scalability.
Structure:

    UI Layer (Compose Composables):
        Renders UI elements.
        Connected to the ViewModel for state management.

    ViewModel:
        Exposes data and actions to the UI using StateFlow.
        Communicates with the repository for data operations.

    Repository:
        Encapsulates Firebase interactions.
        Provides data and business logic to the ViewModel.

Modules:

    App: Main entry point of the application.
    Core: Common code shared between modules.
    Home: Displays balance and transactions.
    SignIn: Handles login and authentication.
    SignUp: Manages the onboarding process.
    Transactions: Provides transaction details.

🔄 Asynchronous Handling

    Kotlin Coroutines: Efficient background task management.
    Flow: Streams data asynchronously to ensure the UI stays updated.

🔍 Features

    Registration Flow:
        Collect personal data and ID photo.
        Display a success message upon completion.

    Authentication:
        Secure Email and Password login.
        Redirect users to the home screen upon login.

    Bank Information:
        View balance and transaction history.
        Access transaction details on item click.

    Test Data Setup:
        Firebase preloaded with sample data to facilitate testing.

🛠️ Improvements

I know there are many areas for improvement, but I’ve identified the most important on

    Create a design system module:
        This module will house molecules and all related composables, ensuring a consistent look and feel across the entire application.

    Set up a centralized theme:
        A shared theme across the app ensures visual consistency. This can reside in the design system module for better structure.

    Offline Mode:
        Cache data locally for offline access to balances and transactions.

    Increase test coverage:
        Increase unit and UI test coverage with JUnit and Espresso.
        Add integration tests for Firebase interactions.

    Enhance screen designs:
        Currently, the focus has been on functionality. There’s room to apply improved UI/UX designs for a better user experience.

    Scalability:
        Implement pagination for the transactions list.
        Consider migrating to Firestore for more advanced queries.
        Consider use UseCase for most robust application

🔑 Test Credentials

If you want to log in without creating a user, you can use the following credentials:

    Email: aochoa0@ucol.mx
    Password: Yahoo123

Any user that you create or log in to will receive random balance and transactions. This is purely to demonstrate how the app works.