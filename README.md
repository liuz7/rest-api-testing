## HTTP rest api testing framework

###### The most lightweight testing DSL based on java 
 
1. Dependencies
    - JDK8, Groovy2+, Gradle3+.
    
2. How to open project. 
    - Use the Intellj IDEA/Eclipse to open the gradle project by selecting the `ci-service-test/build.gradle`.
    
3. How to run tests.
    - In Intellj IDEA, open `gradle tool` panel, run the `test` or `build` task in specific project.
    - Or in terminal, run `gradle test` in specific project directory (The gradle runtime is installed).
    - Or in terminal, run `./gradlew test` in specific project directory (The gradle runtime is not installed).
    
4. Where to add test cases.
    - In `src/test/java` directory, the TestNg test cases can bed added. Those can be ran by 
      IDE test runner.
          
5. Where to open the test report.
    - Open `build/reports/tests/html/index.html` to open the test report.
    