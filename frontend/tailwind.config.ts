import type { Config } from 'tailwindcss'

export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      fontFamily: {
        sans: ['Inter', 'sans-serif'],
      },

      colors: {
        primary: {
          700: 'var(--color-primary-700)',
          500: 'var(--color-primary-500)',
          300: 'var(--color-primary-300)',
        },

        bg: {
          dark: 'var(--color-bg-dark)',
          panel: 'var(--color-bg-panel)',
          light: 'var(--color-bg-light)',
        },

        text: {
          primary: 'var(--color-text-primary)',
          muted: 'var(--color-text-muted)',
          dark: 'var(--color-text-dark)',
        },

        border: 'var(--color-border)',
        divider: 'var(--color-divider)',

        success: 'var(--color-success)',
        warning: 'var(--color-warning)',
        error: 'var(--color-error)',
        info: 'var(--color-info)',
      },
    },
  },
} satisfies Config
