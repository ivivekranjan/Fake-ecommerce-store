# Fake E-Commerce Store API Automation

This is an **API Test Automation Framework** built for a fake e-commerce store application.  
The framework is implemented using **Rest Assured + TestNG + Maven** with reusable utilities, schema validation, retry mechanism, and reporting.

---

##  Features
- ✅ Automated API test cases for **Products, Cart, Authentication, and End-to-End flows**
- ✅ **Rest Assured** for API requests and responses
- ✅ **TestNG** for test management and parallel execution
- ✅ **Retry Analyzer** for rerunning failed test cases
- ✅ **JSON Schema Validation** for response structure verification
- ✅ **Maven** for dependency management and build
- ✅ **Extensible structure** for adding more modules/services
- ✅ Generates **HTML TestNG Reports** after execution

---

##  Project Structure

Fake-ecommerce-store/
├── src/main/java/base/ # Base classes (BaseTest, API utils)

├── src/main/java/utils/ # Utilities (SchemaValidator, RetryAnalyzer)

├── src/test/java/tests/ # Test classes (CartTests, ProductTests, etc.)

├── src/test/resources/schemas/ # JSON schemas for validation

├── pom.xml # Maven dependencies


##  Tech Stack
- **Java 17** 
- **Rest Assured**
- **TestNG**
- **Maven**
- **Hamcrest / JSON Schema Validator**

View TestNG HTML reports:
After execution, open test-output/index.html in a browser.

👤 Author

Vivek Ranjan


