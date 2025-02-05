# 🎮 VideoGame API Test Automation  

## 📌 Overview  
This project is an automated test suite for the **VideoGameDB API** using **Java, RestAssured, JUnit 5, and Allure Reports**.  
It includes **authentication, CRUD operations, and error handling tests**.  

---  

## 🚀 Tech Stack  
- **Java 21+** (or later)  
- **RestAssured** (API Testing)  
- **JUnit 5** (Test Framework)  
- **Maven** (Build Tool)  
- **Allure Reports** (Test Reporting)  
- **GitHub Actions** (CI/CD)  

---  

## 🛠 Setup Instructions  

### **1️⃣ Install Java & Maven**  

#### **🔹 Install Java (21+)**  
- **Windows:**  
  1. Download and install [Java 21+](https://adoptopenjdk.net/).  
  2. Set the `JAVA_HOME` environment variable.  
  3. Verify installation:  
     ```sh
     java -version
     ```

- **macOS (Homebrew):**  
  ```sh
  brew install openjdk@21
  echo 'export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
  source ~/.zshrc
  ```

- **Linux (Ubuntu/Debian):**  
  ```sh
  sudo apt update
  sudo apt install openjdk-21-jdk
  java -version
  ```

#### **🔹 Install Maven**  
- **Windows:**  
  1. Download [Apache Maven](https://maven.apache.org/download.cgi).  
  2. Add Maven to the `PATH` environment variable.  
  3. Verify installation:  
     ```sh
     mvn -version
     ```

- **macOS (Homebrew):**  
  ```sh
  brew install maven
  ```

- **Linux (Ubuntu/Debian):**  
  ```sh
  sudo apt update
  sudo apt install maven
  mvn -version
  ```

---

### **2️⃣ Clone the Repository**  
```sh
git clone https://github.com/codemora/fido-test-project.git
cd videogame-api-tests
```

### **3️⃣ Configure API Endpoints**  
- Update the **`config.properties`** file with API endpoints.  
```properties
base.url=https://www.videogamedb.uk:443/api
auth.endpoint=/api/authenticate
videogame.endpoint=/api/videogame
```

### **4️⃣ Install Dependencies**  
```sh
mvn clean install -U
```

---  

## 🧪 Running Tests  

### **1️⃣ Run All Tests**  
```sh
mvn test
```

### **2️⃣ Run a Specific Test Class**  
```sh
mvn -Dtest=VideogameApiTest test
```

### **3️⃣ Generate Allure Report**  
```sh
mvn allure:report
```
- The generated report will be located at:
  ```sh
  target/site/allure-maven-plugin/index.html
  ```

### **4️⃣ Generate and Serve Allure Report**  
Run the following command to generate the report and automatically open it in the browser:
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

