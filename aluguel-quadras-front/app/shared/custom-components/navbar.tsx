import Link from 'next/link'
import { Button } from "@/components/ui/button"
import { Calendar, ClipboardList, MapPin, UserCircle, LogIn, LogOut } from "lucide-react"
import { useAuth } from '../auth/auth-context'
import { useEffect } from 'react'

export function Navbar() {
  const { user, logout } = useAuth()

  return (
    <nav className="bg-white shadow-sm">
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div className="flex justify-between h-16">
        <div className="flex">
          <div className="flex-shrink-0 flex items-center">
            <Link href="/landing-page" className="text-xl font-bold text-gray-900">
              Quaddra
            </Link>
          </div>
          <div className="hidden sm:ml-6 sm:flex sm:space-x-8">

            {
              user? (
                <>
                <NavLink href="/perfil" icon={<ClipboardList className="h-5 w-5" />}>
                Perfil
              </NavLink>
                <NavLink href="/meus-agendamentos" icon={<Calendar className="h-5 w-5" />}>
                Agendamentos
              </NavLink>
              </>
              ):(
                <></>
              )
            }

            <NavLink href="/quadras" icon={<MapPin className="h-5 w-5" />}>
              Quadras
            </NavLink>
          </div>
        </div>
        <div className="hidden sm:ml-6 sm:flex sm:items-center">
          {user ? (
            <div className="flex items-center space-x-4">
              <span className="text-sm text-gray-700">Bem vindo, {user.nome}</span>
              <Button variant="ghost" size="icon" onClick={logout}>
                <LogOut className="h-5 w-5" />
              </Button>
            </div>
          ) : (
            <Button variant="ghost" asChild>
              <Link href="/login" className="flex items-center">
                <LogIn className="h-5 w-5 mr-2" />
                Login
              </Link>
            </Button>
          )}
        </div>
        <div className="flex items-center sm:hidden">
          <Button variant="ghost" size="icon">
            <svg className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </Button>
        </div>
      </div>
    </div>
  </nav>
  )
}

function NavLink({ href, children, icon }) {
  return (
    <Link
      href={href}
      className="inline-flex items-center px-1 pt-1 border-b-2 border-transparent text-sm font-medium text-gray-500 hover:text-gray-700 hover:border-gray-300"
    >
      {icon}
      <span className="ml-2">{children}</span>
    </Link>
  )
}