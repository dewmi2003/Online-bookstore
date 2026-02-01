# Online Bookstore Application

A full-featured e-commerce web application for managing and selling books online, built with Spring Boot and Thymeleaf.

## Features

### Customer Features

- 👤 User Registration & Authentication
- 📚 Browse and Search Books
- 🔍 View Detailed Book Information
- 🛒 Shopping Cart Management
- 📦 Place Orders & View Order History
- 🔐 Secure Login System

### Admin Features

- ➕ Add New Books
- ✏️ Edit Book Details
- 🗑️ Delete Books (Soft Delete)
- 📸 Upload Book Cover Images
- 📊 Manage Book Categories
- 👥 View All Orders

## Technologies Used

- **Backend**: Spring Boot 3.2.2
- **Language**: Java 17
- **Database**: MySQL
- **Security**: Spring Security with BCrypt
- **Template Engine**: Thymeleaf
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Frontend**: HTML, CSS, JavaScript

## Prerequisites

Before running this application, make sure you have the following installed:

1. **Java Development Kit (JDK) 17** or higher
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **Maven 3.6+**
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn -version`

3. **MySQL Server 8.0+**
   - Download from: https://dev.mysql.com/downloads/mysql/
   - Verify installation: `mysql --version`

4. **Git** (optional, for cloning)
   - Download from: https://git-scm.com/downloads

## Database Setup

1. **Start MySQL Server**

2. **Create Database** (Optional - application will auto-create)

   ```sql
   CREATE DATABASE bookstoredb;
   ```

3. **Update Database Credentials** (if needed)

   Open `src/main/resources/application.properties` and update:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```

## Installation & Setup

### Step 1: Clone or Download the Project

```bash
# If using Git
git clone <repository-url>
cd Online-bookstore

# Or simply navigate to the project directory
cd "C:\Users\T.M.Malith Sandeepa\OneDrive\Desktop\Projects\Online-bookstore"
```

### Step 2: Configure Upload Directory

Create a directory for book cover images or update the path in `application.properties`:

```properties
app.upload.dir=C:/bookstore/uploads
```

Create the directory:

```bash
mkdir C:\bookstore\uploads
```

### Step 3: Build the Project

```bash
mvn clean install
```

### Step 4: Run the Application

```bash
mvn spring-boot:run
```

Or run directly with Java:

```bash
java -jar target/bookstore-0.0.1-SNAPSHOT.jar
```

### Step 5: Access the Application

Open your web browser and navigate to:

```
http://localhost:8080
```

## Default Login Credentials

### Admin Account

- **Email**: `admin@bookstore.com`
- **Password**: `admin123`

### Create Customer Account

- Register a new account at: `http://localhost:8080/register`

## Application Structure

```
Online-bookstore/
├── src/
│   ├── main/
│   │   ├── java/com/bookstore/
│   │   │   ├── config/           # Security & initialization configs
│   │   │   ├── controller/       # MVC controllers
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── entity/           # JPA entities
│   │   │   ├── exception/        # Exception handlers
│   │   │   ├── repository/       # Data access layer
│   │   │   └── service/          # Business logic layer
│   │   └── resources/
│   │       ├── static/           # CSS, JS, images
│   │       ├── templates/        # Thymeleaf templates
│   │       └── application.properties
├── pom.xml
└── README.md
```

## Available Endpoints

### Public Access

- `GET /` - Home page (redirects to books)
- `GET /books` - Browse all books
- `GET /book/{id}` - View book details
- `GET /login` - Login page
- `GET /register` - Registration page
- `POST /register` - Submit registration

### Authenticated Users

- `GET /cart` - View shopping cart
- `POST /cart/add` - Add item to cart
- `POST /cart/update` - Update cart item quantity
- `GET /cart/remove/{bookId}` - Remove item from cart
- `GET /checkout` - Process checkout
- `GET /orders` - View order history
- `GET /order/confirmation/{id}` - Order confirmation

### Admin Only

- `GET /admin/dashboard` - Admin dashboard
- `GET /admin/books` - Manage books
- `GET /admin/books/new` - Add new book form
- `POST /admin/books` - Save new book
- `GET /admin/books/edit/{id}` - Edit book form
- `POST /admin/books/{id}` - Update book
- `GET /admin/books/delete/{id}` - Delete book

## Troubleshooting

### Port Already in Use

If port 8080 is already in use, change it in `application.properties`:

```properties
server.port=8081
```

### Database Connection Issues

- Ensure MySQL server is running
- Verify database credentials in `application.properties`
- Check if the database exists or set `createDatabaseIfNotExist=true`

### Upload Directory Issues

- Ensure the upload directory exists
- Check write permissions for the directory
- Verify the path is correctly set in `application.properties`

### Build Failures

- Ensure Java 17 is installed and configured
- Clear Maven cache: `mvn clean`
- Update dependencies: `mvn clean install -U`

## Development Mode

To run in development mode with auto-reload:

```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

## Testing

Run all tests:

```bash
mvn test
```

## Building for Production

Create a production-ready JAR:

```bash
mvn clean package -DskipTests
```

The JAR file will be created in `target/bookstore-0.0.1-SNAPSHOT.jar`

## Future Enhancements

- [ ] Payment gateway integration
- [ ] Email notifications
- [ ] Book reviews and ratings
- [ ] Inventory management
- [ ] Advanced search filters
- [ ] Wishlist functionality
- [ ] Order tracking
- [ ] PDF invoice generation

## License

This project is for educational purposes.

## Contact

For questions or support, please contact the development team.

---

**Happy Reading! 📚**
