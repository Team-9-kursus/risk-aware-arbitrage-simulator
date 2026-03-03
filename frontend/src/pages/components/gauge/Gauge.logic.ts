export type GaugeCalculation = {
  clampedValue: number
  normalized: number
  circumference: number
  offset: number
  color: string
}

const clamp = (v: number, min: number, max: number): number => {
  if (v < min) return min
  if (v > max) return max
  return v
}

const getColor = (value: number, higherBetter: boolean): string => {
  if (higherBetter) {
    if (value <= 30) return 'stroke-error'
    if (value <= 60) return 'stroke-warning'
    return 'stroke-success'
  }
  if (value <= 30) return 'stroke-success'
  if (value <= 60) return 'stroke-warning'
  return 'stroke-error'
}

export const calculateGauge = (value: number, higherBetter: boolean): GaugeCalculation => {
  const clampedValue = clamp(value, 0, 100)
  const normalized = clampedValue / 100
  const radius = 50
  const circumference = 2 * Math.PI * radius
  const offset = circumference * (1 - normalized)
  const color = getColor(clampedValue, higherBetter)

  return { clampedValue, normalized, circumference, offset, color }
}
