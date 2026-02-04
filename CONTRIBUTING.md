# Contributing

## Workflow
- Create a new branch from `main` (no direct pushes to `main`).
- Open a Pull Request (PR) and request review.
- Merge only after required CI checks pass.

## Branch naming
Use one of:
- `feat/<short-name>`
- `fix/<short-name>`
- `docs/<short-name>`
- `chore/<short-name>`

## Commits
Use Conventional Commits:
- `feat: ...`
- `fix: ...`
- `docs: ...`
- `chore: ...`

## CI / checks
Before requesting review, ensure:
- Backend builds (`mvn -DskipTests clean compile`)
- CI “backend-build” is green on the PR

## Scope
Keep PRs small and focused (one purpose per PR).