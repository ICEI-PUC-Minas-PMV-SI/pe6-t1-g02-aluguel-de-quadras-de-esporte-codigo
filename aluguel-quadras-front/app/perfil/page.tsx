'use client'

import { useEffect, useState } from 'react'
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { useToast } from "@/hooks/use-toast"
import { Toaster } from "@/components/ui/toaster"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import apiService from "app/shared/services/api-service"
import { AuthProvider, useAuth } from '../shared/auth/auth-context'
import { useRouter } from 'next/navigation'

export default function AlterarPerfil() {
  const user = useAuth().user
  const { toast } = useToast()
  const router = useRouter()
  const [formData, setFormData] = useState({
    nome: user?.nome,
    telefone: user?.telefone,
    novaSenha: '',
    confirmarNovaSenha: ''
  })
  const [passwordError, setPasswordError] = useState('')

  useEffect(() => {
    if (user == null) {
      alert("Sua sessão expirou!");
      return
    }

    apiService.getUser(user.id).then((u) => {
      console.log(u)
    })
  }, [])

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setFormData(prev => ({ ...prev, [name]: value }))

    if (name === 'novaSenha' || name === 'confirmarNovaSenha') {
      if (name === 'novaSenha' && formData.confirmarNovaSenha && value !== formData.confirmarNovaSenha) {
        setPasswordError('As senhas não coincidem')
      } else if (name === 'confirmarNovaSenha' && value !== formData.novaSenha) {
        setPasswordError('As senhas não coincidem')
      } else {
        setPasswordError('')
      }
    }
  }

  const formatarTelefone = (value: string) => {
    value = value.replace(/\D/g, "");
    if (value.length > 10) {
      return `(${value.substring(0, 2)}) ${value.substring(2, 7)}-${value.substring(7, 11)}`;
    } else if (value.length > 5) {
      return `(${value.substring(0, 2)}) ${value.substring(2, 6)}-${value.substring(6, 10)}`;
    } else if (value.length > 2) {
      return `(${value.substring(0, 2)}) ${value.substring(2)}`;
    } else {
      return value;
    }
  };

  const handleTelefoneChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const telefoneFormatado = formatarTelefone(e.target.value)
    setFormData(prev => ({ ...prev, telefone: telefoneFormatado }))
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()

    if (!formData.nome || !formData.telefone || !formData.novaSenha || !formData.confirmarNovaSenha) {
      toast({
        title: "Erro",
        description: "Todos os campos são obrigatórios",
        variant: "destructive",
      })
      return
    }

    if (passwordError) {
      toast({
        title: "Erro",
        description: "As senhas não coincidem.",
        variant: "destructive",
      })
      return
    }

    const userData = {
      senha: formData.novaSenha,
      telefone: formData.telefone ?? "",
      nome: formData.nome ?? "",
    };


    apiService.editaUsuario(userData, user!!.id).then(r => {
      toast({
        title: "Sucesso",
        description: "Seu perfil foi atualizado com sucesso.",
      })
      // Limpar o formulário após o sucesso
      setFormData({
        nome: '',
        telefone: '',
        senhaAtual: '',
        novaSenha: '',
        confirmarNovaSenha: ''
      })
      router.push('/landing-page');
    })
      .catch(e => {
        console.error('Erro ao atualizar perfil:', e)
        toast({
          title: "Erro",
          description: "Ocorreu um erro ao atualizar seu perfil. Por favor, tente novamente.",
          variant: "destructive",
        })
      })
  }

  return (
    <AuthProvider>
      <Card className="w-full max-w-md mx-auto mt-10">
        <CardHeader>
          <CardTitle>Alterar Perfil</CardTitle>
          <CardDescription>Atualize suas informações pessoais aqui.</CardDescription>
        </CardHeader>
        <Toaster />
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="space-y-2">
              <Label htmlFor="nome">Nome</Label>
              <Input
                id="nome"
                name="nome"
                value={formData.nome}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="telefone">Telefone</Label>
              <Input
                id="telefone"
                name="telefone"
                value={formData.telefone}
                onChange={handleTelefoneChange}
                placeholder="(00) 00000-0000"
                required
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="novaSenha">Nova Senha</Label>
              <Input
                id="novaSenha"
                name="novaSenha"
                type="password"
                value={formData.novaSenha}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className="space-y-2">
              <Label htmlFor="confirmarNovaSenha">Confirmar Nova Senha</Label>
              <Input
                id="confirmarNovaSenha"
                name="confirmarNovaSenha"
                type="password"
                value={formData.confirmarNovaSenha}
                onChange={handleInputChange}
                required
              />
              {passwordError && <p className="text-red-500 text-sm mt-1">{passwordError}</p>}
            </div>
          </form>
        </CardContent>
        <CardFooter>
          <Button type="submit" className="w-full" onClick={handleSubmit}>Atualizar Perfil</Button>
        </CardFooter>
      </Card>
    </AuthProvider>
  )
}