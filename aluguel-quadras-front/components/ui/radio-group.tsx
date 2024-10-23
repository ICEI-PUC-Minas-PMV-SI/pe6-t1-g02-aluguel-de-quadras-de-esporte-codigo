'use client'

import React, { createContext, useContext } from 'react'

interface RadioGroupContextType {
  value: string
  onValueChange: (value: string) => void
}

const RadioGroupContext = createContext<RadioGroupContextType | undefined>(undefined)

interface RadioGroupProps {
  value: string
  onValueChange: (value: string) => void
  children: React.ReactNode
  className?: string
}

export const RadioGroup: React.FC<RadioGroupProps> = ({ value, onValueChange, children, className }) => {
  return (
    <RadioGroupContext.Provider value={{ value, onValueChange }}>
      <div className={className}>{children}</div>
    </RadioGroupContext.Provider>
  )
}

interface RadioGroupItemProps {
  value: string
  id: string
  className?: string
}

export const RadioGroupItem: React.FC<RadioGroupItemProps> = ({ value, id, className }) => {
  const context = useContext(RadioGroupContext)
  if (!context) {
    throw new Error('RadioGroupItem must be used within a RadioGroup')
  }

  const { value: groupValue, onValueChange } = context

  return (
    <input
      type="radio"
      id={id}
      value={value}
      checked={value === groupValue}
      onChange={() => onValueChange(value.toString())}
      className={`form-radio h-4 w-4 text-primary focus:ring-primary ${className}`}
    />
  )
}
