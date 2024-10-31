'use client'

import { useDeferredValue, useEffect, useState } from 'react'
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { useToast } from "@/hooks/use-toast"
import { Toaster } from "@/components/ui/toaster"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import apiService from "app/shared/services/api-service"
import { AuthProvider, useAuth } from '../shared/auth/auth-context'

export default function AlterarPerfil() {
    const user = useAuth().user
  const { toast } = useToast()
  const [formData, setFormData] = useState({
    nome: '',
    telefone: '',
    senhaAtual: '',
    novaSenha: '',
    confirmarNovaSenha: ''
  })

  useEffect(()=>{
apiService.getUser(user.id).then((u)=>{
console.log(u)
})
    
  },[])
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setFormData(prev => ({ ...prev, [name]: value }))
  }

  const formatarTelefone = (telefone: string) => {
    const numeros = telefone.replace(/\D/g, '')
    if (numeros.length <= 10) {
      return numeros.replace(/(\d{2})(\d{4})(\d{4})/, '($1) $2-$3')
    } else {
      return numeros.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3')
    }
  }

  const handleTelefoneChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const telefoneFormatado = formatarTelefone(e.target.value)
    setFormData(prev => ({ ...prev, telefone: telefoneFormatado }))
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()

    if (formData.novaSenha !== formData.confirmarNovaSenha) {
      toast({
        title: "Erro",
        description: "As novas senhas não coincidem.",
        variant: "destructive",
      })
      return
    }

    try {
      const resposta = await apiService.atualizarPerfil({
        nome: formData.nome,
        telefone: formData.telefone.replace(/\D/g, ''),
        senha: formData.novaSenha
      })
      
      if (resposta.success) {
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
      } else {
        throw new Error('Falha ao atualizar o perfil')
      }
    } catch (erro) {
      console.error('Erro ao atualizar perfil:', erro)
      toast({
        title: "Erro",
        description: "Ocorreu um erro ao atualizar seu perfil. Por favor, tente novamente.",
        variant: "destructive",
      })
    }
  }

  return (
    <AuthProvider>
    <Card className="w-full max-w-md mx-auto mt-10">
      <CardHeader>
        <CardTitle>Alterar Perfil</CardTitle>
        <CardDescription>Atualize suas informações pessoais aqui.</CardDescription>
      </CardHeader>
      <Toaster></Toaster>
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
            <Label htmlFor="senhaAtual">Senha Atual</Label>
            <Input
              id="senhaAtual"
              name="senhaAtual"
              type="password"
              value={formData.senhaAtual}
              onChange={handleInputChange}
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
            />
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