'use client'

import { useEffect, useState } from 'react'
import { useRouter } from 'next/navigation'
import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { AuthProvider, useAuth } from '../shared/auth/auth-context'

export default function LoginPage() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const router = useRouter()
  const { login } = useAuth()

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setError('')

    try {
      await login(email, password)
      router.push('/landing-page')
    } catch (err) {
      setError('Falha no login. Por favor, verifique suas credenciais.')
    }
  }

  return (
    <div className="min-h-screen flex">
      <div className="hidden lg:flex lg:w-1/2 bg-black items-center justify-center">
        <div className="text-center">
          <h1 className="text-4xl font-bold text-white mb-4">Gestão de quadras esportivas</h1>
          <p className="text-xl text-gray-300">Gerencie seus horários com facilidade</p>
        </div>
      </div>

      <div className="w-full lg:w-1/2 flex items-center justify-center p-8 bg-white">
        <Card className="w-full max-w-md border-gray-200">
          <CardHeader>
            <CardTitle className="text-2xl font-bold text-center text-gray-900">Login</CardTitle>
            <CardDescription className="text-center text-gray-600">
              Entre com suas credenciais para acessar sua conta
            </CardDescription>
          </CardHeader>
          <CardContent>
            <form onSubmit={handleSubmit} className="space-y-4">
              <div className="space-y-2">
                <Label htmlFor="email" className="text-gray-700">Email</Label>
                <Input 
                  id="email" 
                  type="email"  
                  className="w-full border-gray-300" 
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>
              <div className="space-y-2">
                <Label htmlFor="password" className="text-gray-700">Senha</Label>
                <Input 
                  id="password" 
                  type="password" 
                  className="w-full border-gray-300" 
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              {error && <p className="text-red-500 text-sm">{error}</p>}
              <Button type="submit" className="w-full bg-black text-white hover:bg-gray-800">
                Entrar
              </Button>
              <Link className="space-y-2" href="/cadastro">
                <Button className="w-full bg text-white hover:bg-gray-800 mt-4">
                  Criar conta
                </Button>
              </Link>
            </form>
          </CardContent>
        </Card>
      </div>
    </div>
  )
}