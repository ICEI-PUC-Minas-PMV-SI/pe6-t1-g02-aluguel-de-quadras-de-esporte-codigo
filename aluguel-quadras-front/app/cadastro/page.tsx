'use client'

import { useState } from 'react'
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group"
// import { toast } from "@/components/ui/use-toast"

export default function UserRegistration() {
  const [formData, setFormData] = useState({
    name: '',
    phone: '',
    email: '',
    password: '',
    documentType: 'cpf',
    document: ''
  })

  // Função para formatar o telefone
  const formatPhone = (value: string) => {
    value = value.replace(/\D/g, ""); // Remove tudo que não for dígito
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

  // Função para formatar CPF
  const formatCPF = (value: string) => {
    value = value.replace(/\D/g, ""); // Remove tudo que não for dígito
    if (value.length > 11) value = value.substring(0, 11); // Limita a 11 dígitos
    return value.replace(/(\d{3})(\d)/, "$1.$2") // Adiciona ponto
                .replace(/(\d{3})(\d)/, "$1.$2") // Adiciona ponto
                .replace(/(\d{3})(\d{2})/, "$1-$2"); // Adiciona hífen
  };

  // Função para formatar CNPJ
  const formatCNPJ = (value: string) => {
    value = value.replace(/\D/g, ""); // Remove tudo que não for dígito
    if (value.length > 14) value = value.substring(0, 14); // Limita a 14 dígitos
    return value.replace(/(\d{2})(\d)/, "$1.$2") // Adiciona ponto
                .replace(/(\d{3})(\d)/, "$1.$2") // Adiciona ponto
                .replace(/(\d{3})(\d)/, "$1/$2") // Adiciona barra
                .replace(/(\d{4})(\d)/, "$1-$2"); // Adiciona hífen
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    let formattedValue = value;

    if (name === "phone") {
      formattedValue = formatPhone(value);
    } else if (name === "document") {
      // Limita o comprimento e aplica a máscara do CPF ou CNPJ
      if (formData.documentType === 'cpf') {
        formattedValue = formatCPF(value);
      } else if (formData.documentType === 'cnpj') {
        formattedValue = formatCNPJ(value);
      }
    }

    setFormData(prev => ({ ...prev, [name]: formattedValue }));
  };

  const handleDocumentTypeChange = (value: string) => {
    setFormData(prev => ({ ...prev, documentType: value, document: '' }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Aqui você enviaria os dados para o backend
    console.log(formData);
    // toast({
    //   title: "Registro enviado",
    //   description: "Seus dados foram enviados com sucesso!",
    // }, /* secondArgument */)
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
        
        <Button type="submit" className="w-full">Cadastrar</Button>
      </form>
    </div>
  )
}
