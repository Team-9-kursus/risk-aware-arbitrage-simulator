````md
# Design Guidelines

This document defines **UI design rules and color tokens** used in the frontend.
The goal is **consistency, clarity, and low cognitive load** - not visual experimentation.

---

## 1. Design Principles

- Keep It Simple
- One primary color
- Dark-first UI
- Accessibility over aesthetics
- No decorative UI elements

---

## 2. Color Tokens (Design Tokens)

### Primary / Brand

```css
--color-primary-700: #0F3DFF;
--color-primary-500: #2F6BFF;
--color-primary-300: #8FA9FF;
````

**Usage**

* Primary actions (CTA)
* Active states
* Focus / selection
* Icons (primary)

---

### Backgrounds

```css
--color-bg-dark:  #0B0F1A;
--color-bg-panel: #111827;
--color-bg-light: #F9FAFB;
```

**Usage**

* Dark = main app background
* Panel = cards, containers
* Light = optional light mode / documentation views

---

### Text

```css
--color-text-primary: #E5E7EB;
--color-text-muted:   #9CA3AF;
--color-text-dark:    #111827;
```

**Rules**

* No long text in primary blue
* Muted text only for secondary information

---

### Borders / UI Chrome

```css
--color-border:  #1F2937;
--color-divider: #374151;
--color-icon:    #CBD5E1;
```

---

### State Colors

```css
--color-success: #10B981;
--color-warning: #F59E0B;
--color-error:   #EF4444;
--color-info:    #38BDF8;
```

Used **only** for feedback (never decoration).

---

## 3. Gradients

```css
--brand-gradient:
  linear-gradient(135deg, #2F6BFF 0%, #0F3DFF 50%, #0B1DBF 100%);
```

**Rules**

* Max **one gradient per screen**
* Allowed only in:

  * Hero sections
  * App icon
  * Highlight blocks

---

## 4. CSS Setup (Example)

```css
:root {
  --color-primary-700: #0F3DFF;
  --color-primary-500: #2F6BFF;
  --color-primary-300: #8FA9FF;

  --color-bg-dark:  #0B0F1A;
  --color-bg-panel: #111827;
  --color-bg-light: #F9FAFB;

  --color-text-primary: #E5E7EB;
  --color-text-muted:   #9CA3AF;
  --color-text-dark:    #111827;

  --color-border:  #1F2937;
  --color-divider: #374151;

  --color-success: #10B981;
  --color-warning: #F59E0B;
  --color-error:   #EF4444;
  --color-info:    #38BDF8;
}
```

---

## 5. Do / Don’t

### Do

* Reuse tokens everywhere
* Keep UI flat and readable
* Prefer spacing over decoration

### Don’t

* Don’t invent new colors
* Don’t use gradients everywhere
* Don’t override primary color

---

## 6. Scope

This document is **guidance**, not a styling framework.
Implementation details live in frontend code (CSS / Tailwind / UI system).

```