Kukulbank 📱💳

A modern, secure banking app that allows users to register, log in, and view their bank information, including balances and transaction details, built using Jetpack Compose and Firebase.
🚀 Challenge Overview

The goal of Kukulbank was to create a functional banking application with essential user management and banking features. The key tasks were:

    User Registration:
        Collect personal data: Name, surname, email, and password.
        Capture an ID photo.
        Display a success screen after registration.

    User Login:
        Login screen with email and password fields.
        Provide a button to access the registration process (Onboarding).
        Redirect to the Home screen after successful login.

    Bank Information (Home Screen):
        Display user’s balance and list of transactions.
        Provide transaction details on click of a movement item.

    Database:
        Use Firebase for storing user data and transactions.
        Prepopulate Firebase with test users and movements to simplify login testing.

🛠️ Technologies Used

    Jetpack Compose: UI toolkit for building the user interface.
    Compose Navigation: For managing navigation between screens.
    Firebase Realtime Database: Backend to store user and transaction data.
    Firebase Authentication (Email & Password): Secure user authentication.
    MVVM Architecture: Organized separation between UI and business logic.
    Hilt: Dependency injection for easier testing and modularization.
    Kotlin Coroutines and Flow: For asynchronous operations.

🏗️ Architecture

This project follows the MVVM (Model-View-ViewModel) architecture pattern, designed for better separation of concerns and scalability. Here's how each part is structured:

    UI Layer (Compose Composables):
        Responsible for rendering UI elements.
        Connected to the ViewModel for state management.

    ViewModel:
        Exposes data and actions to the UI using StateFlow.
        Communicates with the repository to load or update data.

    Repository:
        Encapsulates data operations, such as accessing Firebase.
        Handles business logic and provides data to the ViewModel.

    Modules:
        App: Main entry point of the application.
        Core: Common code shared between modules.
        Home: Home screen showing balance and transactions.
        SignIn: Login screen and authentication logic.
        SignUp: Onboarding process for new users.
        Transactions: Displays transaction details.

🔄 Asynchronous Handling

    Coroutines: Manage background tasks efficiently.
    Flow: Stream data asynchronously, ensuring up-to-date UI with minimal effort.

🔍 Features

    Registration Flow:
        Users provide personal information and upload their ID photo.
        Success message after registration completion.

    Authentication:
        Secure Email and Password login.
        Navigate users to the home screen upon successful login.

    Bank Information:
        Users can view their current balance and transaction history.
        Clicking a transaction opens detailed information.

    Test Data Setup:
        Firebase preloaded with sample users and transactions to facilitate testing.

🛠️ Improvements

While the project meets the requirements, there are a few improvements that can enhance the app’s performance and usability:

    UI/UX Enhancements:
        Add animations for smooth screen transitions between Onboarding and Login.
        Implement Dark Mode support to improve usability.

    Error Handling:
        Display user-friendly error messages for authentication and network issues.
        Implement retry logic for failed network operations using Flow.

    Offline Mode:
        Cache data locally to allow access to balances and transactions even without an internet connection.

    Security Enhancements:
        Add biometric authentication (Fingerprint/Face ID) for faster login.
        Encrypt sensitive data stored locally using EncryptedSharedPreferences.

    Testing:
        Increase unit and UI test coverage using JUnit and Espresso.
        Implement integration tests to validate interactions with Firebase.

    Continuous Integration:
        Set up a CI/CD pipeline using GitHub Actions or Jenkins for automated testing and deployment.

    Scalability:
        Introduce pagination in the transactions list for better performance with large datasets.
        Migrate to Firestore for more complex query capabilities if needed.

🤝 Conclusion

Kukulbank is a robust, modularized banking application built with modern Android technologies and best practices. It demonstrates proficiency in Android development, MVVM architecture, Firebase integration, and Jetpack Compose. Future improvements aim to enhance the user experience, security, and performance to align with industry standards.