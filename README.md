# 📘 Project Title: Personal Budget Tracker

## 🧠 Overview

**Personal Budget Tracker** is a desktop-based Java application developed using **JavaFX** and **FXML**, designed to help individuals manage their finances efficiently. As an Object-Oriented Programming (OOP) project, this system enables users to create accounts, define budget limits, record expenses, and monitor their financial status through a real-time interactive dashboard.

It emphasizes usability, clean UI design, and modular coding practices. The application is especially suitable for students or individuals who want to better control their personal spending.

---

## 🎯 Objectives

The main goals of this project are to:

- Demonstrate OOP principles in a practical Java application.
- Provide a simple, intuitive interface for personal budget tracking.
- Help users monitor and control income and expenses.
- Showcase JavaFX and FXML usage for modern UI development.

---

## 🔍 Key Features

- **User Account Creation:** Personalized budget and expense tracking for each user.
- **Add Budget:** Users can define a total budget and categorize it (e.g., food, rent, entertainment).
- **Record Expenses:** Input daily expenses with descriptions and amounts, which are deducted from the budget.
- **Dashboard Display:**
  - Total Budget
  - Total Expenses
  - Remaining Balance
  - Expense Breakdown
  - Bar Chart Visualization
- **Modular Design:**
  - Clear separation of UI (FXML) and logic (Java classes).
  - Main classes include:
    - `BudgetData.java`: Handles budget and expense data.
    - `BudgetDashboardController.java`: Manages dashboard logic and updates.
    - `Button_Create_Account.java`: Handles new account creation.

---

## 🏗️ System Architecture & Design

- **JavaFX** for UI components.
- **FXML** for UI layout definition.
- **Controller Classes** for handling user interactions and updating the UI.
- **Java Collections (ArrayList)** for in-memory data storage.
- **CSV File Storage** to save each user's data individually for personal expense reports.

---

## 🔧 Tools & Technologies

| Tool / Technology | Purpose                    |
|-------------------|----------------------------|
| Java              | Core programming language  |
| JavaFX            | GUI development            |
| FXML              | UI layout definition       |
| Scene Builder     | Visual FXML design tool    |
| OOP Principles    | Structure and logic design |

---

## 🧪 How to Use

1. Launch the application using your preferred Java IDE.
2. Create a new user account.
3. Use the interface to:
   - Add your total budget and categorize it.
   - Record expenses with names and values.
4. Navigate to the dashboard to view:
   - Total spending
   - Remaining budget
   - Visual breakdown of expenses

---

## 📝 Limitations

- Desktop-only application (not available on web or mobile).
- Single-user mode (no multi-user session management).

---

## 📚 Learning Outcomes

By building this project, students gain practical experience with:

- Object-Oriented Programming concepts
- JavaFX GUI development
- FXML and Scene Builder integration
- Event-driven programming
- MVC-inspired modular architecture
- Dynamic UI updates and user input handling
