# GitHub Activity Fetcher (Java Console App)

A simple Java console application that fetches and displays recent GitHub activity of any user using the GitHub Events API and `Gson` for JSON parsing.

#### Sample solution for the [github-user-activity](https://roadmap.sh/projects/github-user-activity) challenge from [roadmap.sh](https://roadmap.sh/).

---

## 🚀 Features

- Fetches the latest public GitHub events for any user.
- Displays readable activity like:
    - Pushed commits
    - Starred repositories
    - Opened issues
- Uses Java 11+ HttpClient and Gson for parsing JSON.

---

## 📦 Dependencies

- **Java 11 or higher**
- [Gson Library](https://mvnrepository.com/artifact/com.google.code.gson/gson)

---

## 🛠️ Setup Instructions

### 1. Clone or Download the Project

```bash
git clone https://github.com/mouryasumit0/github-user-activity-cli.git
cd github-user-activity-cli
```

Or download the `.zip` and extract it.

### 2. Add Gson to IntelliJ (if not using Maven/Gradle)

1. Download the latest `gson-*.jar` from [Gson on Maven](https://mvnrepository.com/artifact/com.google.code.gson/gson)
2. Open IntelliJ and go to:
    - `File > Project Structure > Modules > Dependencies`
    - Click the `+` sign → `JARs or directories`
    - Select the downloaded `.jar` file and apply changes

### 3. Run the Application

In IntelliJ, right-click the `Main.java` file and click **Run**.

Or from terminal (assuming `gson-2.10.1.jar` is in your project directory):

```bash
javac -cp gson-2.10.1.jar Main.java
java -cp .:gson-2.10.1.jar Main
```

*(On Windows, replace `:` with `;` in the classpath)*

---

## 💡 Example Output

```
Enter GitHub username: torvalds
- Pushed 2 commit(s) to torvalds/linux
- Starred torvalds/subsurface
- Opened a new issue in torvalds/linux
```

---

## 📌 Notes

- The GitHub API returns only the **latest public events**.
- GitHub limits **unauthenticated requests to 60 per hour**.
- You can modify the code to display more event types (like `ForkEvent`, `PullRequestEvent`, etc.).

---

## 🧑‍💻 Author

Made with ❤️ in Java

---

## 📄 License

This project is licensed under the MIT License - feel free to use and modify it.
