import { describe, it, expect } from 'vitest'
import { calculateGauge } from './Gauge.logic'

describe('calculateGauge', () => {
  const radius = 50
  const circumference = 2 * Math.PI * radius

  it('clamps below 0 when higherBetter=false', () => {
    const result = calculateGauge(-10, false)
    expect(result.clampedValue).toBe(0)
    expect(result.color).toBe('stroke-success')
    expect(result.offset).toBeCloseTo(circumference)
  })

  it('clamps below 0 when higherBetter=true', () => {
    const result = calculateGauge(-10, true)
    expect(result.clampedValue).toBe(0)
    expect(result.color).toBe('stroke-error')
    expect(result.offset).toBeCloseTo(circumference)
  })

  it('boundary 30, higherBetter=false', () => {
    const result = calculateGauge(30, false)
    expect(result.clampedValue).toBe(30)
    expect(result.color).toBe('stroke-success')
    expect(result.offset).toBeCloseTo(circumference * 0.7)
  })

  it('boundary 30, higherBetter=true', () => {
    const result = calculateGauge(30, true)
    expect(result.clampedValue).toBe(30)
    expect(result.color).toBe('stroke-error')
    expect(result.offset).toBeCloseTo(circumference * 0.7)
  })

  it('boundary 60, higherBetter=false', () => {
    const result = calculateGauge(60, false)
    expect(result.clampedValue).toBe(60)
    expect(result.color).toBe('stroke-warning')
    expect(result.offset).toBeCloseTo(circumference * 0.4)
  })

  it('boundary 60, higherBetter=true', () => {
    const result = calculateGauge(60, true)
    expect(result.clampedValue).toBe(60)
    expect(result.color).toBe('stroke-warning')
    expect(result.offset).toBeCloseTo(circumference * 0.4)
  })

  it('clamps above 100, higherBetter=false', () => {
    const result = calculateGauge(150, false)
    expect(result.clampedValue).toBe(100)
    expect(result.color).toBe('stroke-error')
    expect(result.offset).toBeCloseTo(0)
  })

  it('clamps above 100, higherBetter=true', () => {
    const result = calculateGauge(150, true)
    expect(result.clampedValue).toBe(100)
    expect(result.color).toBe('stroke-success')
    expect(result.offset).toBeCloseTo(0)
  })
})
