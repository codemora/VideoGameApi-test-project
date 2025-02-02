# 🎮 VideoGame API Test Automation

## 📌 Overview
This project is an automated test suite for the **VideoGameDB API** using **Java, RestAssured, JUnit 5, and Allure Reports**.  
It includes **authentication, CRUD operations, and error handling tests**.

---

## 🚀 Tech Stack
- **Java 11+** (or later)
- **RestAssured** (API Testing)
- **JUnit 5** (Test Framework)
- **Maven** (Build Tool)
- **Allure Reports** (Test Reporting)
- **GitHub Actions** (CI/CD)

---

## 🔧 Setup Instructions

### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/YOUR_GITHUB_USERNAME/videogame-api-tests.git
cd videogame-api-tests
```

### **2️⃣ Configure API Endpoints**
- Update the **`config.properties`** file with API endpoints.
```properties
base.url=https://www.videogamedb.uk:443/api
auth.endpoint=/api/authenticate
videogame.endpoint=/api/videogame
```

### **3️⃣ Install Dependencies**
```sh
mvn clean install
```

---

## 🧪 Running Tests

### **1️⃣ Run All Tests**
```sh
mvn test
```

### **2️⃣ Run a Specific Test Class**
```sh
mvn -Dtest=VideogameTest test
```

### **3️⃣ Generate Allure Report**
```sh
mvn allure:serve
```

---

## ✅ Test Cases Covered
| Test Suite | Description |
|------------|------------|
| `AuthTest` | Tests authentication and token retrieval |
| `VideogameTest` | Tests **GET/POST/PUT/DELETE** operations for video games |
| `VideogameListTest` | Tests retrieval of all video games |
| `NegativeTests` | Tests API responses for invalid or unauthorized requests |

---

## 📊 GitHub Actions CI/CD
This project includes **GitHub Actions** to run tests automatically on every push/pull request.  
- **Workflow File:** `.github/workflows/test.yml`
- **Allure Reports** are generated and uploaded as artifacts.

### **Running Tests in GitHub Actions**
1. **Push your changes** to GitHub.
2. Check the **Actions** tab in your repository.
3. Download the **Allure Report** from the artifacts section.

---

## 📝 License
This project is **open-source** under the **MIT License**.

---

💡 **Happy Testing! 🚀**

