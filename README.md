# 📚 Online Bookstore

A simple e-commerce web application for buying and managing books online.

## ✨ Features

### For Customers
- Browse and search books
- View book details
- Add books to cart
- Place orders
- Register and login

### For Admins
- Add, edit, and delete books
- Upload book images
- View all orders

## 🛠️ What You Need

1. **Java 17+** - [Download here](https://www.oracle.com/java/technologies/downloads/)
2. **Maven 3.6+** - [Download here](https://maven.apache.org/download.cgi)
3. **MySQL 8.0+** - [Download here](https://dev.mysql.com/downloads/mysql/)

## 🚀 How to Run

### Step 1: Setup Database

Open MySQL and create a database:
```sql
CREATE DATABASE bookstoredb;
```

### Step 2: Update Password

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 3: Run the Application

Open terminal in project folder and run:
```bash
mvn spring-boot:run
```

### Step 4: Open in Browser

Go to: **http://localhost:8080**

## 🔐 Login Details

**Admin Account:**
- Email: `admin@bookstore.com`
- Password: `admin123`

## 📁 Project Structure

```
src/main/java/com/bookstore/
├── controller/    # Handles web requests
├── service/       # Business logic
├── repository/    # Database access
├── entity/        # Database tables
└── config/        # App settings
```

## 🔧 Configuration

All settings are in `src/main/resources/application.properties`:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/bookstoredb
spring.datasource.username=root
spring.datasource.password=admin123

# Upload folder
app.upload.dir=C:/bookstore/uploads
```

## 🐛 Common Problems

**Port 8080 already in use?**
Change port in `application.properties`:
```properties
server.port=8081
```

**Can't connect to database?**
- Make sure MySQL is running
- Check username and password
- Make sure database exists

**Can't upload images?**
Create the upload folder:
```bash
mkdir C:\bookstore\uploads
```

## 📄 License

For educational purposes only.

---

**Made with Spring Boot ❤️**
