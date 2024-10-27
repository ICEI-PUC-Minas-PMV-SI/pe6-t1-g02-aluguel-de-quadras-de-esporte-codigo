import React, { createContext, useState, useEffect, useContext } from 'react'
import apiService from '../services/api-service'
import Usuario from '../services/types/usuario'

interface AuthContextType {
  user: Omit<Usuario, 'telefone' | 'dataCriacao' | 'dataUltimaAtualizacao' | 'dataDelecao'> | null
  loading: boolean
  login: (email: string, password: string) => Promise<void>
  logout: () => void
}

const AuthContext = createContext<AuthContextType | undefined>(undefined)

export function AuthProvider({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<Omit<Usuario, 'telefone' | 'dataCriacao' | 'dataUltimaAtualizacao' | 'dataDelecao'> | null>(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    // Check if user is logged in on initial load
    checkUserLoggedIn()
  }, [])

  const checkUserLoggedIn = async () => {
    try {
      // In a real app, you would make an API call to validate the token
      const token = localStorage.getItem('token')
      const localUser = localStorage.getItem('user')
      if (token && localUser) {
        setUser(JSON.parse(localUser))
      }
    } catch (error) {
      console.error('Failed to check authentication status', error)
    } finally {
      setLoading(false)
    }
  }

  const login = async (email: string, password: string) => {
    try {
      const response = (await apiService.login(email, password)).data
      localStorage.setItem('token', response.token)
      localStorage.setItem('user', JSON.stringify(response.user))
      setUser(response.user)
    } catch (error) {
      console.error('Login failed', error)
      throw error
    }
  }

  const logout = () => {
    localStorage.removeItem('token')
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
    throw new Error('useAuth must be used within an AuthProvider')
  }
  return {
    user: {
      id: "df0cc417-b9f2-4614-af79-01d254ef929a",
      nome: "Enzo"
    }, logout: () => console.log("LOGGING OUT")
  }
  // return context
}