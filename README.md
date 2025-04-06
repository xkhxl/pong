# 🏓 Pong Game in Java

A desktop version of the classic Pong game written in Java.

## 🧠 Tech & Concepts

- Java Swing for GUI (`JFrame`, `JPanel`)
- Event-driven programming with `KeyListener`
- Object-oriented design (`Ball`, `Paddle`, `GamePanel`, etc.)
- Game loop using `Thread`
- Simple `.bat` script to compile and launch

## 🚀 How to Run

### 📍 Option 1: Run via `pong.bat` (Windows)

Just double-click the `pong.bat` file in the root directory. It will:
- Compile the Java files (if not already compiled)
- Launch the game window

### 📍 Option 2: Manual Run

If you're not on Windows or prefer terminal:

```bash
cd "Pong Game - Java/src"
javac -d ../bin WelcomePage.java
java -cp ../bin WelcomePage