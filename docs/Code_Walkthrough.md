# Code Walkthrough

## Adding a student

1. `Main` reads the first name, last name, email and batch.
2. `StudentService.addStudent` checks these values using `InputValidator`.
3. `IDGenerator.getNextStudentId()` supplies the next student ID.
4. The `Student` constructor calls the `Person` constructor with `super(...)`.
5. `StudentRepository.addStudent` saves the object in its ArrayList.
6. The service returns the student so `Main` can print it.

The constructor uses `this.firstName = firstName` to assign a parameter to the object's field. Getters read private fields; setters change them. `int` and `boolean` are primitive types, while `String` and `Student` are reference types.

## Finding a record

The repository loops through its list and returns the object with the matching ID. If nothing matches, it returns `null`. The service then throws `EntityNotFoundException`, which the menu catches and displays.

Methods that return several records return an empty list when there are no matches.

## Enrolling a student

`EnrollmentService` finds the student and course through their services. It checks that both are active and that there isn't already an active enrollment for that pair.

It then creates an enrollment with a new ID, today's date and `EnrollmentStatus.ACTIVE`. The enrollment stores the existing student and course IDs. It doesn't generate new IDs for them.

When the enrollment is displayed, `Main` looks up its course ID to get the course name.

## Menu input

The menus use `while` loops and `switch` statements. Returning from a submenu takes the user back to the main menu.

`readInt` reads a whole line and converts it using `Integer.parseInt`. If conversion fails, it asks again. If console input ends, the app closes cleanly.

Parsing isn't the same as typecasting. For example, `(int) 8.5` gives `8`, but `Integer.parseInt("8")` converts text into an integer. Course duration must be entered as a whole number.

## Variable scope and access

Local variables belong to a method call. Entity fields belong to individual objects. Static ID counters belong to the class and are shared across calls.

Public methods can be called from other packages. Private fields and helpers are only accessible within their class. `Main.readInt` has no access modifier, so it can be called from the same package, including the test class.
