# Design Guidelines

This document defines **UI design rules and color tokens** used in the frontend.
The goal is **consistency, clarity, and low cognitive load** - not visual experimentation.

---

## 1. Design Principles

* Keep It Simple
* Dark-first UI
* Accessibility over aesthetics
* No decorative UI elements
* One brand identity color

---

## 2. Color System Overview

We use a **role-based color system**, not random usage.

| Role      | Purpose                     |
| --------- | --------------------------- |
| Primary   | Main brand actions          |
| Secondary | Subtle interactive elements |
| Accent    | Rare emphasis               |
| State     | System feedback only        |

---

## 3. Primary (Brand Color)

Primary is used for **main actions only**.

```css
--color-primary-700: #0F3DFF;
--color-primary-500: #2F6BFF;
--color-primary-300: #8FA9FF;
```

### Usage

* Main CTA buttons
* Active navigation item
* Focus states
* Key interactive highlights

### Rules

* Never use for long text
* Never use for backgrounds
* Do not use more than 1 strong primary element per section

---

## 4. Secondary

Secondary is a **neutral interaction tone**, not brand color.

```css
--color-secondary-500: #64748B;
--color-secondary-300: #94A3B8;
```

### Usage

* Secondary buttons
* Inactive tabs
* Subtle interactive UI

---

## 5. Accent

Accent is rare emphasis - used sparingly.

```css
--color-accent: #38BDF8;
```

### Usage

* Informational highlights
* Minor emphasis blocks

Max **1 accent element per screen**.

---

## 6. Backgrounds

```css
--color-bg-dark:  #0B0F1A;
--color-bg-panel: #111827;
--color-bg-light: #F9FAFB;
```

### Usage

* Dark --> main app background
* Panel --> cards, modals, containers
* Light --> documentation / optional light mode

---

## 7. Text

```css
--color-text-primary: #E5E7EB;
--color-text-muted:   #9CA3AF;
--color-text-dark:    #111827;
```

### Rules

* Never use primary blue for paragraphs
* Muted only for secondary information
* Maintain AA accessibility contrast

---

## 8. Borders / UI Chrome

```css
--color-border:  #1F2937;
--color-divider: #374151;
--color-icon:    #CBD5E1;
```

Used for:

* Dividers
* Card outlines
* Neutral UI structure

---

## 9. State Colors (System Only)

```css
--color-success: #10B981;
--color-warning: #F59E0B;
--color-error:   #EF4444;
--color-info:    #38BDF8;
```

### Strict Rule

State colors are used **only for system feedback**.
Never for decoration.

---

## 10. Gradients

```css
--brand-gradient:
  linear-gradient(135deg, #2F6BFF 0%, #0F3DFF 50%, #0B1DBF 100%);
```

### Rules

* Maximum **one gradient per screen**
* Allowed only in:

  * Hero sections
  * App logo
  * Marketing pages
* Not allowed in core app UI

---

## 11. CSS Setup (Example)

```css
:root {
  --color-primary-700: #0F3DFF;
  --color-primary-500: #2F6BFF;
  --color-primary-300: #8FA9FF;

  --color-secondary-500: #64748B;
  --color-secondary-300: #94A3B8;

  --color-accent: #38BDF8;

  --color-bg-dark:  #0B0F1A;
  --color-bg-panel: #111827;
  --color-bg-light: #F9FAFB;

  --color-text-primary: #E5E7EB;
  --color-text-muted:   #9CA3AF;
  --color-text-dark:    #111827;

  --color-border:  #1F2937;
  --color-divider: #374151;
  --color-icon:    #CBD5E1;

  --color-success: #10B981;
  --color-warning: #F59E0B;
  --color-error:   #EF4444;
  --color-info:    #38BDF8;
}
```

---

## 12. Do / Don’t

### Do

* Reuse tokens everywhere
* Keep UI flat and readable
* Prefer spacing over decoration

### Don’t

* Don’t invent new colors
* Don’t use gradients in app core
* Don’t overload screens with primary elements

---

## 13. Scope

This document defines **design direction and token philosophy**, not implementation details.

Implementation lives in frontend code (CSS / Tailwind / UI system).