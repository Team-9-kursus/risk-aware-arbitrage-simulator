import React from 'react'
import { calculateGauge } from './Gauge.logic'

type GaugeProps = {
  value: number
  higherBetter?: boolean
}

export const Gauge: React.FC<GaugeProps> = ({ value, higherBetter = false }) => {
  const { offset, circumference, color } = calculateGauge(value, higherBetter)
  const radius = 50
  const strokeWidth = 12

  return (
    <div className="relative w-48 h-48 flex items-center justify-center">
      <svg viewBox="0 0 120 120" className="w-full h-full">
        <circle
          cx="60"
          cy="60"
          r={radius}
          className={`${color} fill-transparent stroke-current origin-center -rotate-90`}
          strokeWidth={strokeWidth}
          strokeLinecap="round"
          strokeDasharray={circumference}
          strokeDashoffset={offset}
        />
      </svg>
      <div className="absolute text-4xl font-semibold">{value}</div>
    </div>
  )
}
