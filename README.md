# Secret Santa

A Java Swing application for creating and managing Secret Santa games. Users can import or create players, add gift ideas, manage game information, randomly assign participants, view assignments privately, and export the completed assignments to a JSON file.

## Features

* Import players and gift ideas from a JSON file
* Create, update, and delete players
* Add multiple gift ideas for each player
* Create and update the game title
* Randomly assign Secret Santa pairings without assigning anyone to themselves
* Re-assign players when needed
* Privately reveal a selected player's assignment
* Display the giftee's gift ideas when revealing an assignment
* Automatically hide assignment information after 30 seconds
* Export game information and assignments to JSON

## Requirements

* Java 21 or later
* Apache Maven
* VS Code with the Java Extension Pack (recommended)

The project uses Maven to manage dependencies, compile the application, and run tests.

## Setup

### 1. Install Java
Verify that Java is installed:

```powershell
java -version
javac -version
```

The project is configured to compile for Java 21.

### 2. Install Maven
Install Apache Maven and add Maven's `bin` directory to your Windows `Path`.
Verify the installation with:

```powershell
mvn -v
```

The command should display the installed Maven version and the Java version being used.

### 3. Clone or open the project
Open the `secretsanta` directory in VS Code. This directory should contain the `pom.xml` file.

```text
SecretSanta/
└── secretsanta/
    ├── pom.xml
    └── src/
```

### 4. Install Maven dependencies
From the directory containing `pom.xml`, run:

```powershell
mvn clean compile
```

Maven will download the project's dependencies and compile the source code.
The project uses:
* Jackson for reading and writing JSON
* JUnit 5 for unit tests

## Running the Application

### Using VS Code
Open:

```text
src/main/java/io/github/b314/GiftGameUI.java
```

Find the `main` method and click the **Run** button that appears above it.
The application should open the Secret Santa Swing GUI.

```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(GiftGameUI::new);
}
```

## Using the Application

### Importing Players
Use **Import Players** to select a JSON file containing the game title, players, and gift ideas.
A basic input file has the following structure:

```json
{
  "title": "2026 Secret Santa",
  "players": [
    {
      "name": "Alice",
      "gifts": [
        "Books",
        "Coffee",
        "Board Games"
      ]
    },
    {
      "name": "Bob",
      "gifts": [
        "Headphones",
        "Games"
      ]
    }
  ]
}
```

### Creating Players
Select **Create Player** and enter:
1. The player's name
2. Their gift ideas, separated by commas

### Managing Players
Select a player from the player list to:
* **Update Player** — change the player's information
* **Delete Player** — remove the player from the game

### Assigning Players
Select **Assign Players** to create the Secret Santa assignments. 
The assignment algorithm ensures that a participant cannot be assigned to themselves. 
After assignments have been created, the button changes to **Re-assign Players**, allowing a new set of assignments to be generated.

### Viewing an Assignment
Select a player and choose **View Assignment**. 
The application displays that player's assigned giftee along with the giftee's gift ideas.
The assignment can be hidden using the **X** button and automatically disappears after 30 seconds.

### Exporting Assignments
Select **Export Assignments** to save the current game and assignments as a JSON file.
The exported file can be used to preserve the completed game and its assignments.
To perform a clean build and run all tests:

## Technologies
* **Java 21**
* **Java Swing** — graphical user interface
* **Maven** — project and dependency management
* **Jackson** — JSON parsing and serialization
* **JUnit 5** — unit testing
