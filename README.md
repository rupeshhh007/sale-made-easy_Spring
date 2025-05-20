# 📦 Gada Electronics - CRUD Web Application

This is a full-stack **CRUD** (Create, Read, Update, Delete) web application for managing electronic products, built using:

- 🌱 Spring Boot (Backend)
- 🎨 Thymeleaf (Server-Side Templating)
- 💅 Bootstrap 5 (Frontend Styling)
- 🧠 MVC Architecture (Model-View-Controller)
- 💾 Spring Data JPA & H2/SQL Database

---

## 🔧 Features

- 🔍 View a list of electronic products
- ➕ Add new products with image upload
- 📝 Edit existing product details
- ❌ Delete products
- 🖼️ Upload and display product images
- 🧱 Category-based dropdown for classification

---

## 📁 Project Structure

```bash
src/
├── main/
│   ├── java/com/yourpackage/
│   │   ├── controller/        # Handles web requests
│   │   ├── models/            # Contains Product and ProductDTO
│   │   └── repository/        # JPA Repository interface
│   ├── resources/
│   │   ├── static/images/     # Stores uploaded images
│   │   ├── templates/         # Thymeleaf HTML views
│   │   └── application.properties
└── ...
