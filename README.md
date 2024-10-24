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

While the project meets the requirements, these improvements can further enhance its usability:

    UI/UX Enhancements:
        Add animations for smooth screen transitions.
        Implement Dark Mode for better usability.

    Error Handling:
        Show user-friendly error messages.
        Use Flow to implement retry logic for network failures.

    Offline Mode:
        Cache data locally for offline access to balances and transactions.

    Security Enhancements:
        Add biometric authentication (Fingerprint/Face ID).
        Encrypt sensitive data using EncryptedSharedPreferences.

    Testing:
        Increase unit and UI test coverage with JUnit and Espresso.
        Add integration tests for Firebase interactions.

    Continuous Integration:
        Set up a CI/CD pipeline with GitHub Actions or Jenkins for automated testing and deployment.

    Scalability:
        Implement pagination for the transactions list.
        Consider migrating to Firestore for more advanced queries.

🤝 Conclusion

Kukulbank is a robust, modularized banking application built with modern Android technologies and best practices. It demonstrates expertise in Android development, MVVM architecture, Firebase integration, and Jetpack Compose. Future improvements aim to further enhance the user experience, security, and scalability of the application.