# Dependencies Download Instructions

## Required Libraries

StudyConnect uses FlatLaf for modern UI. You need to download the following JAR files:

### Option 1: Using Maven (Automatic)

Maven will automatically download dependencies. Just run:

```bash
mvn clean package
```

### Option 2: Manual Download

If you're not using Maven, download these files manually:

#### 1. FlatLaf Core

- **File:** flatlaf-3.2.5.jar
- **URL:** https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar
- **Direct Download:** [Click Here](https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar)

#### 2. FlatLaf IntelliJ Themes

- **File:** flatlaf-intellij-themes-3.2.5.jar
- **URL:** https://repo1.maven.org/maven2/com/formdev/flatlaf-intellij-themes/3.2.5/flatlaf-intellij-themes-3.2.5.jar
- **Direct Download:** [Click Here](https://repo1.maven.org/maven2/com/formdev/flatlaf-intellij-themes/3.2.5/flatlaf-intellij-themes-3.2.5.jar)

### Installation Steps

1. **Create lib folder** in the project root:

   ```
   TestNet/
   └── lib/
   ```

2. **Download the JAR files** using the links above

3. **Place JAR files in lib folder:**

   ```
   TestNet/
   └── lib/
       ├── flatlaf-3.2.5.jar
       └── flatlaf-intellij-themes-3.2.5.jar
   ```

4. **Compile the project:**

   ```bash
   # Windows
   compile-manual.bat

   # Linux/Mac
   chmod +x compile-manual.sh
   ./compile-manual.sh
   ```

5. **Run the application:**

   ```bash
   # Windows
   run-manual.bat

   # Linux/Mac
   chmod +x run-manual.sh
   ./run-manual.sh
   ```

## Alternative: GitHub Releases

You can also download from the official FlatLaf GitHub releases:

**Repository:** https://github.com/JFormDesigner/FlatLaf/releases

1. Go to the releases page
2. Find version 3.2.5
3. Download the JAR files
4. Place in lib folder

## Verification

After downloading, verify the files:

### Windows (Command Prompt):

```cmd
dir lib
```

You should see:

```
flatlaf-3.2.5.jar
flatlaf-intellij-themes-3.2.5.jar
```

### Linux/Mac (Terminal):

```bash
ls -l lib/
```

You should see both JAR files listed.

## Troubleshooting

### "File not found" error

- Check that JAR files are in the `lib` folder
- Verify file names are exactly as specified
- Check file extensions are `.jar` not `.jar.zip`

### "Unsupported class file version" error

- Update Java to version 8 or higher
- Check Java version: `java -version`

### Maven download fails

- Check internet connection
- Try using a VPN if corporate firewall blocks Maven Central
- Use manual download method instead

## Additional Resources

- **FlatLaf Documentation:** https://www.formdev.com/flatlaf/
- **Maven Central:** https://search.maven.org/
- **Java Download:** https://www.oracle.com/java/technologies/downloads/

## Support

If you encounter issues:

1. Check the USER_GUIDE.md for troubleshooting
2. Verify all files are in correct locations
3. Ensure Java 8+ is installed
4. Try the alternative download methods

---

**Note:** This application is for educational purposes. The dependencies are open-source and licensed under Apache License 2.0.
