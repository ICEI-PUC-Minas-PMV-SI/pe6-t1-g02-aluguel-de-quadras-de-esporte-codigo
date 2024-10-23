import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Card, CardContent, CardDescription, CardHeader, CardTitle, CardFooter } from "@/components/ui/card"
import { ArrowLeft } from "lucide-react"

export default function Page() {
  return (
    <div className="min-h-screen flex">
      {/* Left side (hidden on small screens) */}
      <div className="hidden lg:flex lg:w-1/2 bg-black items-center justify-center">
        <div className="text-center">
          <h1 className="text-4xl font-bold text-white mb-4">Sports Court Scheduler</h1>
          <p className="text-xl text-gray-300">Manage your court schedules with ease</p>
        </div>
      </div>

      {/* Right side (full width on small screens, 50% on large screens) */}
      <div className="w-full lg:w-1/2 flex items-center justify-center p-8 bg-white">
        <Card className="w-full max-w-md border-gray-200">
          <CardHeader>
            <CardTitle className="text-2xl font-bold text-center text-gray-900">Login</CardTitle>
            <CardDescription className="text-center text-gray-600">
              Entre com suas credenciais para acessar sua conta
            </CardDescription>
          </CardHeader>
          <CardContent>
            <form className="space-y-4">
              <div className="space-y-2">
                <Label htmlFor="email" className="text-gray-700">Email</Label>
                <Input id="email" type="email"  className="w-full border-gray-300" />
              </div>
              <div className="space-y-2">
                <Label htmlFor="password" className="text-gray-700">Senha</Label>
                <Input id="password" type="password" className="w-full border-gray-300" />
              </div>
              <Button type="submit" className="w-full bg-black text-white hover:bg-gray-800">
                Entrar
              </Button>
              <Link href="/cadastro">
                <Button className="w-full bg-red-500 text-white hover:bg-gray-800">
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
