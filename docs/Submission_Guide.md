# GitHub Submission

Repository: [shivangisudan/LearnTrack](https://github.com/shivangisudan/LearnTrack)

The assignment asks for a public repository and a pull request. A pull request shows the changes on a branch and gives the reviewer a place to leave feedback.

## Before committing

Run the app and tests:

```sh
sh run.sh
sh test.sh
```

Check `git status`, `git diff` and `git diff --cached` to see what will go into the commit. Compiled files and local IntelliJ settings should stay out of it.

## Commit and push

From the project folder, on `feature/learntrack`:

```sh
git add src docs tests README.md run.sh test.sh .gitignore
git diff --cached
git commit -m "Complete LearnTrack console management system"
git push -u origin feature/learntrack
```

## Open a pull request

1. On GitHub, open **Pull requests > New pull request**.
2. Set the base branch to `master` and the compare branch to `feature/learntrack`.
3. Check the changed files.
4. Add a title and a short description of the changes and tests.
5. Create the pull request and copy its link.

PR #1 contains the earlier work and has already been merged. The final changes can go in a new PR. Leave it open for review unless the instructor asks you to merge it.

Submit the repository link and the new PR link. Make sure the repository is public so the instructor can view them.
