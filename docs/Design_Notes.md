# Design Notes

## Why ArrayList?

The number of students, courses and enrollments changes as records are added. An array has a fixed size, while an `ArrayList` grows when needed. It also gives us methods like `add` and `isEmpty`.

Each repository keeps its own private list. Searches use a loop to find a matching ID. List methods return a new list so clearing the returned list won't delete stored records. The objects inside are still shared, so changes to a student's details are visible in the repository.

## Static members

`IDGenerator` uses separate static counters for students, courses and enrollments. Each counter is shared across calls and starts at zero. The first ID returned is 1. These counters reset when the app closes.

`InputValidator` methods are static because they only check the arguments passed to them. `AppConstants` and `MenuOptions` use `static final` fields for shared messages and menu numbers.

The repositories and services are regular objects, created once in `Main` and passed to the classes that need them.

## Inheritance and constructors

`Student` extends `Person`, which holds the ID, name and email fields. The student constructor calls `super(...)` to set those fields, then sets the batch and active flag.

There is also a constructor without email. It calls the other constructor using `this(...)` and passes an empty string for email. `StudentService.addStudent` has the same two options.

`Student` overrides `getDisplayName()` to include the batch. When `Main` calls it through a `Person` reference, Java runs the student's version. This is where the project uses polymorphism.

## How the classes work together

- Entities hold the details of a record.
- Repositories store records and search by ID.
- Services check input and handle rules such as duplicate enrollments.
- `Main` reads input, calls services and prints the results.

For example, adding a student calls `StudentService`, which validates the details, gets an ID, creates the student and saves it through `StudentRepository`.

An enrollment stores student and course IDs. When displaying it, `Main` asks `CourseService` for the matching course so it can print the name as well as the ID.

## Enums, validation and errors

`EnrollmentStatus` has three values: `ACTIVE`, `COMPLETED` and `CANCELLED`. Using an enum avoids misspelled status strings. Course activation only needs a boolean.

The services reject blank required fields, invalid email input and non-positive course durations. All student update fields are checked before any are changed. Email is optional and has a basic format check.

Repositories return `null` when an ID isn't found. Services turn that into `EntityNotFoundException`. Invalid details or disallowed actions throw `IllegalArgumentException`. The menus catch these errors and show a message.

`Main.readInt` retries when the user enters text or a number outside the integer range. It has package-private access so the test in the same package can call it.

## Enrollment rules

Both the student and course must be active for a new enrollment. A student cannot have two active enrollments in the same course.

An active enrollment can be completed or cancelled. Once that happens, its status cannot change again, but a new enrollment can be created. Deactivating a student or course keeps the records and their enrollment history.

All records are kept in memory and cleared when the app closes.
