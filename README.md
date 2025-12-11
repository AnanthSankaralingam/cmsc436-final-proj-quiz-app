# CMSC Infinity

**Problem**: took a lot of CMSC courses but forget after while

**Target Users**: Current and recent grad UMD CS

**Solution**: app with high level multiple choice quizzes to refresh knowledge

### User Flow
1. Open app → MainActivity (landing page)
2. Tap "Start Quiz" → QuizActivity
3. Answer questions (see ads between questions)
4. Complete quiz → ResultsActivity shows score
5. Option to view Leaderboard or return Home
   

## Implementation Plan

### Models
1. Question - Quiz question structure
2. QuizSession - Current quiz state tracker
3. UserProfile - User stats and settings
4. LeaderboardEntry - Ranking data

### Views, Controllers
1. MainActivity (Landing Page). welcome message, buttons for start quiz, open profile, see leaderboard, etc.
2. QuizActivity (Question answer page). ads between questions or at bottom of page. pass data to results via Intent. use Question, QuizSession model
3. ResultsActivity (score page). use UserProfile, LeaderboardEntry model
4. LeaderBoardActivity. display top scores and rank from Firebase. use UserProfile, LeaderboardEntry model
---

## ✅ Project Requirements Checklist

- [X ] **1. App Icon (4 pts)** - Nice looking custom icon for the app
- [x ] **2. MVC Architecture (8 pts)** - Proper Model-View-Controller implementation
- [x ] **3. Model Usage (8 pts)** - All data read from and written to Model (QuizSession)
- [X ] **4. Multiple Views (6 pts)** - At least 3 distinct views implemented (Main, Quiz, results, leaderboard)
- [ x] **5. Data Sharing (5 pts)** - At least 2 views share or pass data between them (quiz and results)
- [x ] **6. Local Persistent Data (6 pts)** - At least 2 meaningful persistent variables (user preferences for dark mode, settings for class filter, last score)
- [x ] **7. Remote Persistent Data (10 pts)** - Meaningful remote data storage (quiz questions, user scores)
- [x ] **8. New GUI Components (6 pts)** - At least 2 new components not covered in class (progress bar, switch)
  - [X ] Component 1: progress bar
  - [x ] Component 2: dark mode switch
- [X ] **9. Event Listeners (7 pts)** - Meaningful listener on at least one NEW GUI component (switch, answer choice buttons)
- [x ] **10. App/Hardware/Google Services (10 pts)** - Integration with phone feature or Google service (email, leaderboard via firebase)
- [X ] **11. Meaningful Functionality (20 pts)**
  - [X ] Meaningful/nice functionality (10 pts)
  - [ x] Good looking/free flowing UI (10 pts)
- [x ] **12. Advertising (10 pts)** - Fake ad from Google services (bottom of Main)
