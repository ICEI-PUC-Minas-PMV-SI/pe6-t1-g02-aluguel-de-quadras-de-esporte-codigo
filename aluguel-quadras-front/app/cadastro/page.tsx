"use client"

import { useState } from 'react'
import { useRouter } from 'next/navigation'
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group"
import apiService from "@/app/shared/services/api-service"

export default function UserRegistration() {
  const router = useRouter()
  const [formData, setFormData] = useState({
    name: '',
    phone: '',
    email: '',
    password: '',
    confirmPassword: '',
    documentType: 'cpf',
    document: ''
  })
  const [passwordError, setPasswordError] = useState('')

  const formatPhone = (value: string) => {
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

  const formatCPF = (value: string) => {
    value = value.replace(/\D/g, "");
    if (value.length > 11) value = value.substring(0, 11);
    return value.replace(/(\d{3})(\d)/, "$1.$2")
                .replace(/(\d{3})(\d)/, "$1.$2")
                .replace(/(\d{3})(\d{2})/, "$1-$2");
  };

  const formatCNPJ = (value: string) => {
    value = value.replace(/\D/g, "");
    if (value.length > 14) value = value.substring(0, 14);
    return value.replace(/(\d{2})(\d)/, "$1.$2")
                .replace(/(\d{3})(\d)/, "$1.$2")
                .replace(/(\d{3})(\d)/, "$1/$2")
                .replace(/(\d{4})(\d)/, "$1-$2");
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    let formattedValue = value;

    if (name === "phone") {
      formattedValue = formatPhone(value);
    } else if (name === "document") {
      if (formData.documentType === 'cpf') {
        formattedValue = formatCPF(value);
      } else if (formData.documentType === 'cnpj') {
        formattedValue = formatCNPJ(value);
      }
    }

    setFormData(prev => ({ ...prev, [name]: formattedValue }));

    if (name === 'password' || name === 'confirmPassword') {
      validatePasswords(name === 'password' ? formattedValue : formData.password, name === 'confirmPassword' ? formattedValue : formData.confirmPassword);
    }
  };

  const validatePasswords = (password: string, confirmPassword: string) => {
    if (password !== confirmPassword) {
      setPasswordError('As senhas não coincidem');
    } else {
      setPasswordError('');
    }
  };

  const handleDocumentTypeChange = (value: string) => {
    setFormData(prev => ({ ...prev, documentType: value, document: '' }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setPasswordError('As senhas não coincidem');
      return;
    }

    const userData = {
      nome: formData.name,
      telefone: formData.phone,
      email: formData.email,
      senha: formData.password,
      cpf: formData.documentType === 'cpf' ? formData.document.replace(/\D/g,'') : undefined,
      cnpj: formData.documentType === 'cnpj' ? formData.document.replace(/\D/g,'') : undefined,
    };

    try {
      const response = await apiService.criarUsuario(userData);
      console.log('Registro enviado com sucesso:', response.data);
      router.push('/login');
    } catch (error) {
      console.error('Erro ao enviar registro:', error);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10">
      <form onSubmit={handleSubmit} className="space-y-6">
        <h2 className="text-2xl font-bold text-center">Cadastro de Usuário</h2>
        
        <div>
          <Label htmlFor="name">Nome</Label>
          <Input id="name" name="name" value={formData.name} onChange={handleInputChange} required />
        </div>
        
        <div>
          <Label htmlFor="phone">Telefone</Label>
          <Input id="phone" name="phone" type="tel" value={formData.phone} onChange={handleInputChange} required />
        </div>
        
        <div>
          <Label htmlFor="email">E-mail</Label>
          <Input id="email" name="email" type="email" value={formData.email} onChange={handleInputChange} required />
        </div>
        
        <div>
          <Label htmlFor="password">Senha</Label>
          <Input id="password" name="password" type="password" value={formData.password} onChange={handleInputChange} required />
        </div>

        <div>
          <Label htmlFor="confirmPassword">Confirmar Senha</Label>
          <Input id="confirmPassword" name="confirmPassword" type="password" value={formData.confirmPassword} onChange={handleInputChange} required />
          {passwordError && <p className="text-red-500 text-sm mt-1">{passwordError}</p>}
        </div>
        
        <div>
          <Label>Tipo de Documento</Label>
          <RadioGroup value={formData.documentType} onValueChange={handleDocumentTypeChange} className="flex space-x-4">
            <div className="flex items-center space-x-2">
              <RadioGroupItem value="cpf" id="cpf" />
              <Label htmlFor="cpf">CPF</Label>
            </div>
            <div className="flex items-center space-x-2">
              <RadioGroupItem value="cnpj" id="cnpj" />
              <Label htmlFor="cnpj">CNPJ</Label>
            </div>
          </RadioGroup>
        </div>
        
        <div>
          <Label htmlFor="document">{formData.documentType.toUpperCase()}</Label>
          <Input 
            id="document" 
            name="document" 
            value={formData.document} 
            onChange={handleInputChange} 
            placeholder={formData.documentType === 'cpf' ? '000.000.000-00' : '00.000.000/0000-00'}
            required 
          />
        </div>
        
        <Button type="submit" className="w-full" disabled={!!passwordError}>Cadastrar</Button>
      </form>
    </div>
  )
}