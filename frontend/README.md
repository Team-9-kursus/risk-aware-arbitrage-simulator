# Frontend Application

A modern, type-safe React application built with **React 19**, **TanStack Router**, and **Tailwind CSS v4**.

## Tech Stack

- **Framework:** [React 19](https://react.dev/) (w/ React Compiler)
- **Routing:** [TanStack Router](https://tanstack.com/router) (File-based, Type-safe)
- **Styling:** [Tailwind CSS v4](https://tailwindcss.com/)
- **Build Tool:** [Vite 6](https://vitejs.dev/)
- **Language:** [TypeScript](https://www.typescriptlang.org/)
- **Package Manager:** [pnpm](https://pnpm.io/)
- **Linting:** ESLint 9+ (Flat Config)
- **Formatting:** Prettier (Integrated via ESLint)
- **Testing:** [Vitest](https://vitest.dev/)

## Project Structure

Based on the current architecture:

```text
frontend/
├── src/
│   ├── pages/             # View components and logic
│   │   ├── components/    # Reusable UI (StatCard, State, etc.)
│   │   └── types/         # Page-specific TypeScript definitions
│   ├── routes/            # TanStack Router route definitions
│   ├── stores/            # Global state management
│   ├── index.css          # Tailwind 4 & Global styles
│   ├── main.tsx           # App entry point
│   └── routeTree.gen.ts   # Auto-generated TanStack route tree
├── .prettierrc            # Single source of truth for formatting
├── Dockerfile             # Multi-stage production build
├── eslint.config.js       # ESLint & Prettier integration
├── tailwind.config.ts     # Tailwind configuration
└── tsconfig.json          # TypeScript configuration
```

## Getting Started

### Installation

```bash
pnpm install
```

### Development

```bash
pnpm dev
```
The application uses **File-based Routing**. When you add a new file to `src/routes/`, the `routeTree.gen.ts` will update automatically to provide full type-safety for your links and params.

## Testing with Vitest

Unit tests live **next to the components or pages they test** and **must end with `.test.ts` or `.test.tsx`**.

### Running Tests
```
pnpm test
```

## Quality Control

This project uses a unified linting and formatting strategy. **`.prettierrc`** is the single source of truth for code style.

- **Automated Squiggles:** Formatting errors (like missing spaces or wrong quotes) appear as **red lines** in your editor.
- **Run Linting:** `pnpm lint`
- **Fix Formatting:** `pnpm format` (Uses the rules defined in `.prettierrc`)

## Available Scripts

| Script | Description |
| :--- | :--- |
| `pnpm dev` | Starts the Vite development server |
| `pnpm build` | Type-checks and builds for production |
| `pnpm lint` | Checks for code errors and style violations |
| `pnpm format` | Formats all source code using Prettier |
| `pnpm test` | Run tests |