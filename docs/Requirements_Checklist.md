# Assignment Checklist

| Requirement | Where to find it |
| --- | --- |
| JDK setup and Hello World | `Setup_Instructions.md` |
| JDK, JRE, JVM and bytecode | `JVM_Basics.md` |
| Packages and access modifiers | Packages under `com.airtribe.learntrack`; public methods, private fields and package-private `Main.readInt` |
| Encapsulation | Private entity fields with getters and setters |
| Constructors and overloading | Entity constructors; `Student` and `StudentService.addStudent` with and without email |
| Inheritance and polymorphism | `Student extends Person`, `super(...)` and the overridden `getDisplayName()` |
| Static members | `IDGenerator`, validators and constants |
| Student management | Add, list, find, update and deactivate |
| Course management | Add, list, find, activate and deactivate |
| Enrollment management | Enroll, list by student, find, complete and cancel |
| ArrayList storage | The three repository classes |
| Menu and input handling | `Main` |
| Custom exception | `EntityNotFoundException` |
| Design explanation | `Design_Notes.md` |
| Run instructions and class diagram | `README.md` |
| Tests | `LearnTrackFlowTest.java` and `Testing.md` |

Course duration is measured in weeks. Enrollment status uses an enum; student and course activation use booleans. The app uses Core Java and stores data in memory.

For submission, the repository must be public and the final changes must be submitted through a pull request. See [submission steps](Submission_Guide.md).
