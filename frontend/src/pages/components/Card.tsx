import type { FC, ReactNode } from 'react'

type CardProps = {
  title?: string
  children?: ReactNode
}

export const Card: FC<CardProps> = ({ title, children }) => {
  return (
    <div className="p-4 bg-panel border-4 border-border rounded-2xl break-all">
      {title && <h4>{title}</h4>}
      <div>{children}</div>
    </div>
  )
}
