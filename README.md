# 🎮 Tic Tac Toe Game (Java + Maven + Git)

 A simple console-based Tic Tac Toe game developed using Java and built with Apache Maven, demonstrating essential DevOps practices such as build automation, version control, and executable JAR creation.

# 📌 Project Overview

This project was developed as part of an academic assignment to gain hands-on experience with DevOps tools. The application is a two-player Tic Tac Toe game that runs in the terminal and can be executed using a generated JAR file without any IDE dependency.

The project integrates:

Java for application logic

Maven for build automation

Git & GitHub for version control

# 🛠️ Technologies Used

Programming Language: Java

Build Tool: Apache Maven

Version Control: Git

Repository Hosting: GitHub

Execution Environment: Command Line / Terminal

# 📂 Project Structure

tic-tac-toe/
│
├── src/
│   └── main/
│       └── java/
│           └── TicTacToe.java
│
├── pom.xml
├── README.md
└── target/
    └── tic-tac-toe-1.0.jar

# ⚙️ Maven Commands Used
mvn clean
mvn compile
mvn package


mvn clean – Removes previous build files

mvn compile – Compiles Java source code

mvn package – Generates an executable JAR file

# ▶️ How to Run the Project
Step 1: Build the Project
mvn clean package

Step 2: Run the JAR File
java -jar target/tic-tac-toe-1.0.jar

# 🎯 Game Features

Two-player turn-based gameplay

Console-based user interface

Input validation for moves

Automatic win detection

Draw (tie) condition handling

Clear messages after win, loss, or draw

# 🧠 Core Game Logic

The game uses a 3×3 board represented by a 2D array

Players alternate turns using symbols X and O

Winning conditions are checked after every move:

Horizontal

Vertical

Diagonal

The game ends when a player wins or all cells are filled

# 📦 JAR File Generation

The project is configured using pom.xml to generate an executable JAR file, allowing the application to run independently of any IDE. This demonstrates proper Maven configuration and build automation.
