# Java_SieuThiMini
Đồ án java app quản lí siêu thị mini
- Lập trình theo mô hình 3 lớp
- readme_content = """# 🛒 Java_SieuThiMini: Mini Supermarket Management App

Welcome to the **Java_SieuThiMini** repository! This is a comprehensive Java desktop application designed to streamline and manage the daily operations of a mini supermarket. 

## Architecture
This project is strictly developed using the **3-Tier Architecture (Mô hình 3 lớp)** to ensure a clean separation of concerns, high maintainability, and scalability:
1. **Presentation (GUI) Layer:** Handles the graphical user interface and captures user interactions.
2. **Business Logic Layer (BLL):** Contains the core business rules, calculations, and data processing logic.
3. **Data Access Layer (DAL):** Manages all database connections and CRUD (Create, Read, Update, Delete) operations.

## Key Features
- **Product & Inventory Management:** Add, update, delete, and track product quantities, prices, and categories.
- **Sales & Point of Sale (POS):** Process customer orders, calculate total amounts, and generate detailed invoices.
- **Employee Management:** Manage staff details, roles, and system access levels.
- **Customer Management:** Track customer information and purchase history.
- **Reporting & Statistics:** View sales revenue summaries and inventory status.

## Tech Stack
- **Language:** Java
- **GUI Framework:** Java Swing
- **Database Connectivity:** JDBC
- **Database:** MySQL / SQL Server *(update based on your actual DB)*
- **Design Pattern:** 3-Tier Architecture

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- An IDE like IntelliJ IDEA, Eclipse, or NetBeans
- MySQL or SQL Server database installed locally

### Installation & Setup
1. Clone the repository:
2. ```bash
   git clone [https://github.com/poromvp/Java_SieuThiMini.git](https://github.com/poromvp/Java_SieuThiMini.git)
   cd Java_SieuThiMini
   ```
3. Database Setup:
- Execute the provided SQL script (usually located in the database/ or sql/ folder) in your database management tool to create the required tables and sample data.
Update the database connection credentials (URL, username, password) inside the Data Access Layer (DAL) configuration file.
Open the project in your IDE, build it, and run the main entry point of the application.
