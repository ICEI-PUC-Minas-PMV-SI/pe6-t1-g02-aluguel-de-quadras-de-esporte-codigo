'use client'

import React, { createContext, useState, useEffect, useContext } from 'react'
import Usuario from '../services/types/usuario'

interface AuthContextType {
  user: Omit<Usuario, 'dataCriacao' | 'dataUltimaAtualizacao' | 'dataDelecao'> | null
  loading: boolean
  login: (email: string, password: string) => Promise<void>
  logout: () => void
}

const AuthContext = createContext<AuthContextType | undefined>(undefined)

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<Omit<Usuario, 'telefone' | 'dataCriacao' | 'dataUltimaAtualizacao' | 'dataDelecao'> | null>(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    checkUserLoggedIn()
  }, [])

  const checkUserLoggedIn = async () => {
    try {
      const token = localStorage.getItem('token')
      const localUser = localStorage.getItem('user')
      if (token && localUser) {
        setUser(JSON.parse(localUser))
      }
    } catch (error) {
      console.error('Falha ao verificar o status de autenticação', error)
    } finally {
      setLoading(false)
    }
  }

  const login = async (email: string, password: string) => {
    try {
      const response = await fetch('http://localhost:8080/api/v1/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, senha: password }),
      })

      if (!response.ok) {
        throw new Error('Falha na autenticação')
      }

      const data = await response.json()
      localStorage.setItem('token', data.token)
      localStorage.setItem('user', JSON.stringify(data.user))
      setUser(data.user)
    } catch (error) {
      console.error('Falha no login', error)
      throw error
    }
  }

  const logout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    setUser(null)
  }

  const value = {
    user,
    loading,
    login,
    logout
  }

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
  const context = useContext(AuthContext)
  if (context === undefined) {
    throw new Error('useAuth deve ser usado dentro de um AuthProvider')
  }
  return context
}