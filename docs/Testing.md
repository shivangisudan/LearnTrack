# Testing

## Run the tests

From the project folder:

```sh
sh test.sh
```

The test class uses Core Java. If a check fails, it throws `AssertionError` with a message. It checks:

- Constructors, inheritance, display methods and ID counters.
- Adding, finding, updating and deactivating students.
- Adding, finding, activating and deactivating courses.
- Blank fields, email input, invalid durations and missing IDs.
- Invalid updates leaving the original student details unchanged.
- Changes to returned lists not removing stored records.
- Enrollment links, dates, duplicate enrollments and inactive records.
- Completion, cancellation and enrolling again afterwards.
- Empty lists and invalid integer input.

## Test results — 11 October 2026

- 50 service, entity and input checks passed on JDK 21 and JDK 24.
- Compilation passed without lint warnings.
- A full console session passed 30 output checks.
- Six cases where input ended during a menu or form closed cleanly.
- Course names matched their IDs when creating, listing and finding enrollments. Names also appeared for completed/cancelled enrollments and after deactivating a course.

## Check through the menus

1. List students and courses before adding anything. Both should be empty.
2. Add a student and a course. Note their IDs.
3. Find them by ID. Update the student and check the new details.
4. Enroll the student. Check the course name, course ID and `ACTIVE` status.
5. Try the same enrollment again. It should show a duplicate error.
6. Complete the enrollment, then try cancelling it. It should reject the change.
7. Enroll again and cancel the new enrollment. Both should appear in the history.
8. Add a second course and enroll in it. Check that each enrollment shows the correct name.
9. Deactivate a course. Its history should still be visible, but new enrollments should be rejected.
10. Try enrolling an inactive student and entering missing IDs.
11. Enter text, a blank line or an oversized number at a numeric prompt. It should ask again.
12. Try blank required fields and zero or negative course durations.
13. Exit and restart. The records should be empty again.
