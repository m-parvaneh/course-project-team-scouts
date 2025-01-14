# Progress Report

- [Summaries](#summaries)
  - [Specification](#specification)
  - [CRC Model](#crc-model)
  - [Scenario Walkthrough](#scenario-walkthrough)
    - [Scenario A](#scenario-a)
    - [Scenario B](#scenario-b)
  - [Skeleton Program](#skeleton-program)
- [The Good Parts](#the-good-parts)
- [Questions and Concerns](#questions-and-concerns)
- [Group Member Roles](#group-member-roles)
  - [Aditya](#aditya)
  - [Daniel](#daniel)
  - [Kaartik](#kaartik)
  - [Matthew](#matthew)
  - [Michael](#michael)
  - [Tobey](#tobey)

## Summaries

### Specification

In essence, our applicaiton is a tool to search for players and teams in a soccer database, which is useful for the purposes of [scouting](https://en.wikipedia.org/wiki/Scout_(association_football)). The user (a scout) can search through the database by specifying attributes or simply searching by name. Player attributes include age, physique (*e.g.* height, weight), and skills (*e.g.* passing, tackling, speed).

### CRC Model

Our [CRC model](https://github.com/CSC207-UofT/course-project-team-scouts/blob/main/phase0/crc_model.pdf) currently contains:
- **3 entities:**
  - `Player`, `Team`, and `Scout`
- **9 use cases:**
  - `Player`/`Team`/`ScoutDatabase` (for adding and storing entities)
  - `PlayerStats`/`TeamStatsCalculator` (for calculating entity statistics, *e.g.* averages of attributes)
  - `SearchByPlayerAttributes`/`TeamAttributes` (for searching based on attributes)
    - **Note:** `SearchByTeamAttributes` does not have a dedicated card (very similar functionality to `SearchByPlayerAttributes`)
  - `SearchForPlayer`/`ForTeam` (for searching based on name)
    - **Note:** `SearchForTeam` does not have a dedicated card (very similar functionality to `SearchForPlayer`)
- **8 interface adapters** (including interfaces and classes):
  - `InputPlayerAttributes` and `InputPlayerName` (for prompting user, accepting attribute/name values)
    - These classes implement the `InputData` interface
  - `Players`/`TeamsPresenter` (for presenting information about players or teams in a list)
    - These classes implement the `PresentData` interface
  - `CVSAdapter` (for scraping all player/team information from a .csv file)
    - Implements the `InputAdapter` interface
    - Relies on the [`OpenCSV` external libray](http://opencsv.sourceforge.net/)
- **1 framework** (UI class):
  - `CommandLine` (for controlling program flow; calling `CSVAdapter` when the program starts, calling input methods, etc.)

### Scenario Walkthrough

1. A startup/welcome message appears.
2. The user is asked to choose what they're searching for (players or teams).
3. The user chooses player. Now they must choose between searching by attributes or by name.

#### Scenario A

4. The user chooses to search by attributes.
5. The user is asked to specify a value for each attribute.
6. The process repeats until all values have been entered, and then a search is made.
7. The results of the search are displayed in a table which contains players' names and attributes.

#### Scenario B

4. The user chooses to search by name.
5. The user is asked to specify a player name.
6. The system searches for all players whose name contains the user's input.
7. The results of the search are displayed in a table which contains players' names and attributes.

### Skeleton Program

- The program is run from the `CommandLine` class (it contains a `main()` method).
  - `main()` creates a new instance of `CSVAdapter`, and then runs `CSVAdapter.dataDump`, which calls `PlayerDatabase.add_entity` for each row in the .csv file.
  - `main()` calls `runPrompts`, which effectively starts the UI.
- **Note:** to ensure that `CSVAdapter` runs, you must have `OpenCSV` libary and its dependencies added to your class path. IntelliJ should do this automatically by reading from the `pom.xml` Maven project file.
  - If this doesn't happen automatically, you can try going to `View > Tool Windows > Maven` and click the reload button.
- Our skeleton program does not make use of the `Scout` or `Team` entities, even though they are implemented. Our search methods also need a lot of polishing before they're complete.
  - However, our skeleton program functions as a proof of concept, and it should be easily expandable because of our adherence to Clean Architecture.

## The Good Parts

- `CSVAdapter` works perfectly with our dataset, and the `InputAdapter` interface ensures that if we were to use another data source, most of our code could remain unchanged.
  - *e.g.* if we needed to read from an SQL database instead, we could write an `SQLAdapter` class that has the same functionality. This makes our program extremely adaptable.
- All of our classes have distinct roles and responsibilities. We knew what each class would need to take as input and give as output, even before anything was implemented. This made it fairly easy to collaborate.
  - If we were working on a class that depended on a not yet implemented class, we could code as if it was fully functional.
  - Then, when everyone had completed their tasks, our classes (mostly) fit together like a jigsaw puzzle.

## Questions and Concerns

- Creating interfaces at the boundaries of each Clean Architecture layer?
  - It seems like our design obeys the dependence rules (outer layers depend on inner layers, but not the other way around), so is it necessary to have boundary interfaces?
    - *e.g.* Slide 15 in the Clean Architecture intro lecture
- Private instance variables vs. public instance variables?
  - *i.e.* When should we be using getters and setters rather than accessing variables directly?

## Group Member Roles

The original specification and most CRC cards were a collaborative effort. Almost every major design choice was made in a group call with everyone present.

### Aditya

Worked on the unittest, and helped create the use case classes. I also helped Kaartik implement the SearchForPlayer class and worked with Matthew to find a way to display data in the Players Presenter class. In the future I would like to do some more research and find a way to implement GetSearchQuery, modify or rework on InputPlayerAttributes and analyze how we could put implement and put the StatsCalculator interface to use.

### Daniel

Produced final versions of all written materials (this document, the specification, walkthrough, and CRC cards). 
Wrote a more detailed, "behind-the-scenes" scenario walkthrough that helped us get organized and better understand the responsibilities of each class. 
Collaborated with Tobey on the `CommandLine` and search classes. 
Helped plan out the unit tests with Aditya. 
In the future, I'd like to focus more on the user interface and overall flow of the program. 
I am also interested in implementing a "smarter" attribute-based searching class which takes into account weights and ranges of values. 
I also enjoy writing documents like these 🙂.

### Kaartik

Implemented SearchForPLayer and helped implement SearchByPlayerAttributes. Assisted with InputPlayerAttributesTest and Players Class and resolve simple errors and warnings in Input classes. Researched and found the dataset. In the future I would like to work and delve deeper into scouting algorithms which would enrich the user experience and also make the scouting system advanced. Would also like to work on reducing the runtime and load of the program.

### Matthew

Worked on the `Player` and `Scout` entity classes. Also
implemented the Presenter functionality, creating the `PresentData`
interface and implementing the `PlayersPresenter` class with Aditya.
Researched how to output data in a tabular fashion and format strings
in order to present results of a search in the most readable way possible.
Past Phase 0, I plan to continue working on the Presenter functionality,
implementing a presenter class that works to output data
about entire Teams, and another presenter class that returns result of a search for a singular
player. Additionally, I would like to help out with the development
of classes that calculate and present statistics aggregated across
entire Teams, as well aiding Michael in handling the data side
of the program.

### Michael

Implemented CsvAdapter and PlayerDatabase, assisted with implementing Player and reformating the Specification. Researched  and found data sources and querying approcahes. In the future I would like to stick to the data side of things, and reimplement PlayerDatabase, TeamDatabase, CsvAdapter when we have more features and more complex and heavy data, I'd also be interested in implementing a more sophisticated search algorithms in the search classes. 

### Tobey

Implemented InputData, InputPlayerName, InputPlayerAttributes, CommmandLine, and PlayerPropertiesIterator. Assisted with implementing SearchByPlayerAttributes and SearchForPlayer. Researched design choices in terms of clean input and prompting, as well as helped along the design process of the CRC cards. Moving forward, I would prefer to continue working with user input (and possibly output) and reimplement InputPlayerAttributes and SearchByPlayerAttributes when we finally settle on the searching and attribute features of our Players database. In addition, I would definitely be interested in developing a handy amount of unit tests with respect to the classes that I had been involved in developing. 
