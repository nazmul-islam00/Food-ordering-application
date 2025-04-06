# 🍽️ Food Ordering Application

This is a basic food ordering system developed in **Java**, featuring separate graphical interfaces (GUIs) for customers and restaurants. It simulates a simple ordering workflow between users and food vendors.

## 📁 Project Structure
```bash
Food-ordering-application/
├── src/
│   ├── CustomerGUI.java
│   ├── RestaurantGUI.java
│   ├── OrderManager.java
│   └── Main.java
├── menu.txt
├── restaurant.txt
├── user.txt
├── pom.xml (if using Maven)
└── README.md
```

## ✨ Features

### 👤 Customer Interface
- Browse available menu items
- Place food orders
- View past orders

### 🏪 Restaurant Interface
- Add or remove menu items
- View incoming customer orders
- Update order status (e.g., "Preparing", "Delivered")


## 🧰 Requirements

- Java Development Kit (JDK 11 or higher recommended for JavaFX)
- JavaFX SDK (if not using a JDK that bundles JavaFX)
- (Optional) Maven or Gradle for build automation


## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/nazmul-islam00/Food-ordering-application.git
   cd Food-ordering-application
   ```
2. **If Using Maven (Recommended)**
   Make sure your pom.xml includes JavaFX dependencies.
   Then build and run:

   ```bash
   mvn clean javafx:run
   ```
3. **Manual Compilation (Without Maven)**
   If you're compiling manually, make sure to add JavaFX modules to the classpath:

   ```bash
   
   javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml src/*.java
   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml Main
   ```
   Replace ``/path/to/javafx-sdk`` with the location of your JavaFX installation.

## 🖥️ Usage
- Launch the app to open the Customer GUI or Restaurant GUI
- Add menu items, place orders, and manage the order lifecycle
- Data is saved and read from text files like ``menu.txt``, ``restaurant.txt``, and ``user.txt``


## 📝 License
This repository is licensed under the [MIT License](LICENSE).

## 🙌 Acknowledgments
- [JavaFX](https://openjfx.io/) for GUI framework”
