# xtrspc: Android APK Comparison Tool**

xtrspc is an APK designed for comparing two Android APK files. It decomposes the APKs, identifies added, removed, modified, and unchanged files, generates a detailed comparison result, and extracts relevant data into separate ZIP files. This tool aids in understanding the differences between two APKs and can be useful for version control or security analysis.

**Usage:**
Fill the first EditText with Original APK path  
Fill the second EditText with Modified APK path  

**Features:**
- Decompile APKs using apktool  
- Identify added, removed, modified, and unchanged files  
- Generate a detailed comparison result in a text file  
- Extract and compress important data based on the comparison result  

**Note:**
Remember to grant all required permission.  
If somehow it keeps resulting an error saying some command (example: apktool) is not exists, you could try to input this command ```!reinstall``` on the first EditText and press the **COMPARE APK** button.  

**Author:**
[Gameye98](https://github.com/Gameye98)

**License:**
[MIT](https://github.com/Gameye98/xtrspc/blob/main/README.md)
