# CMSC Infinity
---

## Overview

**CMSC Infinity** is an Android application built to help **University of Maryland Computer Science students** quickly review and retain core concepts from their CMSC courses. Over time, even the best students forget details from older classes—this app makes it easy to refresh key topics through interactive quizzes.

[![Watch the video](https://img.youtube.com/vi/ZYGu1eUsBi8/maxresdefault.jpg)](https://youtu.be/ZYGu1eUsBi8)
---

## 🎯 Problem & Solution

- **Problem:** After taking many CMSC courses, UMD alumni and current students often forget important concepts.  
- **Target Users:** Current and recently graduated UMD CS students.  
- **Solution:** A mobile quiz app that delivers short, high-level multiple-choice quizzes on CS topics, helping users stay sharp through quick review sessions.

---

## 🧭 User Flow

1. **Launch App** → Opens **MainActivity** (landing page)  
2. Tap **“Start Quiz”** → Moves to **QuizActivity**  
3. Answer multiple-choice questions (ads displayed between some questions)  
4. On completion → **ResultsActivity** displays score and performance  
5. User can view **Leaderboard** or return to Home  

---

## ⚙️ Implementation Details

### **Architecture**
The app follows a clean **Model-View-Controller (MVC)** structure for maintainability and clarity.

### **Models**
- **Question:** Defines quiz question structure  
- **QuizSession:** Tracks current quiz state and progress  
- **UserProfile:** Stores user settings, stats, and preferences  
- **LeaderboardEntry:** Represents ranking data from Firebase  

### **Views / Controllers**
- **MainActivity:** Landing page with navigation to quiz, profile, or leaderboard  
- **QuizActivity:** Displays questions, manages answer input, and integrates ads  
- **ResultsActivity:** Shows user’s score and updates profile data  
- **LeaderboardActivity:** Displays top users via Firebase integration  

---

## 💾 Data & Features

- **Local Persistent Data:** Stores user preferences (dark mode, filters, last score)  
- **Remote Persistent Data:** Quiz questions and leaderboard data stored on **Firebase**  
- **Google Services Integration:** Firebase for leaderboard and authentication  
- **Advertising Integration:** Google AdMob mock ad placement for simulation


**Authors**: Ananth Sankaralingam, Sarah Sating, Manasvi Sriranga  


---
