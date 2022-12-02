# My Personal Project

## Movie Sorter

As an avid film enthusiast I wanted to create an application that 
would keep track of the movies I've watched, this application is for others that share 
my interest in watching movies. This application will allow the user to add in movies and then 
categorize, filter, and rank those movies. For the filtering of the movies, 
the user will be able to use this aspect to find certain movie(s), whether it's by name, rating, or category.


 **User Stories**:
 - As a user, I want to be able to add a movie.
 - As a user, I want to be able to view my movies based on category or rating.
 - As a user, I want to be able to rate a movie after adding it to the list.
 - As a user, I want to be able to search the name of one of my movies.
 - As a user, I want to be able to save and load my movie list.
 - As a user, I want to be given the option to save my movie list before quitting.

# Instructions for Grader
 - You can generate the first required event related to adding Xs to a Y by clicking Add Movie 
   and inputting a title and category.
 - You can generate the second required event related to adding Xs to a Y by clicking either the Filter
   by Rating or Filter by Category Button and inputting a value to filter by.
 - You can locate my visual component by loading or saving the Movie List.
 - You can save the state of my application by exiting the application
 - You can reload the state of my application by clicking Load Your Saved Movie List button.

Phase 4 Task 2: Sample Event Log
Thu Dec 01 22:54:23 PST 2022
Searched for: Interstellar

Thu Dec 01 22:54:25 PST 2022
Added: Movie: Interstellar, Category: Sci-fi, Rating: 0

Thu Dec 01 22:54:32 PST 2022
Searched for: Gattaca

Thu Dec 01 22:54:34 PST 2022
Added: Movie: Gattaca, Category: Sci-fi, Rating: 0

Thu Dec 01 22:54:39 PST 2022
Rated: Movie: Gattaca, Category: Sci-fi, Rating: 8

Thu Dec 01 22:54:55 PST 2022
Filtered by category: Sci-fi

Thu Dec 01 22:54:58 PST 2022
Filtered by rating: 7

Thu Dec 01 22:55:00 PST 2022
Filtered by unwatched

Phase 4 Task 3: UML
One item I will be going back on later to fix when I have time:
-refactoring my MovieListUI class to have a separate class for the List Area


Process finished with exit code 0

Citations:
1: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    Modeled the persistence package after.
2: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    Modeled MovieListApp after.
3: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
    Modeled the MoveListUi after. Used the Event and EventLog classes
4: pro-air.de/proair/leistungen_film.html
    directorIcon gif

