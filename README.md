# 🎬 Movie Manager

A desktop application for managing movies and related entities, built as a university project at Algebra University (Software Engineering). Data is fetched via RSS parsing from [Collider.com](https://collider.com/feed/) and stored in a Microsoft SQL Server database.

## 📸 Overview

The solution is a Maven multi-module project consisting of:
- **Utilities** — shared utility classes (file handling, icons, dialogs)
- **Dao** — data access layer with Repository pattern and SQL Server integration
- **ArticleManager** — main Swing GUI application (MVC architecture)

---

## ✨ Features

- 🔐 **Login & Registration** — two roles: Administrator and User
- 🛠️ **Admin panel** — delete all data and reload from RSS feed in one click
- 📡 **RSS Parser** — fetches movie data from Collider.com via `HttpURLConnection` + StAX XML parser
- 🖼️ **Image handling** — downloads and stores images locally with relative paths
- 🎭 **Full CRUD** — Movies, Actors, Directors, Genres with linked entities
- 🖱️ **Drag & Drop** — assign Actors to Movies via drag and drop
- 📤 **XML Export** — export any entity via JAXB
- 📊 **JTable + AbstractTableModel** — dynamic, MVC-compliant data display
- 🧵 **Multithreading** — RSS loading runs on background thread, UI stays responsive

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17+ |
| UI Framework | Java Swing (FlatLaf theme) |
| Architecture | MVC + Repository pattern |
| Build tool | Maven (multi-module) |
| Database | Microsoft SQL Server |
| Data access | JDBC — CallableStatement / PreparedStatement |
| XML parsing | StAX (XMLEventReader) |
| XML export | JAXB |
| IDE | NetBeans 20 |

---

## 🗄️ Database

- DDL initialization script included in `/sql/init.sql`
- Data reset script included in `/sql/clear.sql`
- All CRUD operations execute via stored procedures from Java code
- SQL injection prevention via `PreparedStatement` / `CallableStatement`

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Apache Maven
- Microsoft SQL Server (local or remote)
- NetBeans 20 (recommended) or IntelliJ IDEA

### Setup
1. Clone the repo:
   ```bash
   git clone https://github.com/musamahd/movie-manager.git
   ```
2. Run `/sql/init.sql` on your SQL Server instance to create tables and default admin user
3. Update DB connection settings in `Dao/src/main/resources/db.properties`
4. Open root `pom.xml` in NetBeans → **Build All**
5. Run `ArticleManager` module

---

## 📁 Project Structure

```
MMahdProject/
├── Utilities/          # Shared utilities (FileUtils, IconUtils, DialogUtils)
├── Dao/                # Repository pattern, JDBC, SQL Server
├── ArticleManager/     # Swing GUI — MVC, JFrame, JPanels, JTabbedPane
├── sql/
│   ├── init.sql        # DDL — create tables + default admin
│   └── clear.sql       # Delete all data
└── pom.xml             # Maven parent POM
```

---

## 🎓 Course

Programming in Java 1 — Algebra University, Zagreb (2023/2024)
