'use client'

import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Calendar, Clock, Users } from "lucide-react"
import { useAuth } from '../shared/auth/auth-context'
import { Navbar } from "../shared/custom-components/navbar"

export default function LandingPage() {
  const { user } = useAuth()

  return (
    <>
      <Navbar></Navbar>
      <div className="min-h-screen bg-white">
        <main>
          <section className="py-20 sm:py-32">
            <div className="container mx-auto px-4 sm:px-6 lg:px-8 text-center">
              <h2 className="text-4xl sm:text-5xl font-extrabold text-gray-900 mb-6">
                Simplifique o gerenciamento das suas quadras esportivas de maneira eficiente e descomplicada.
              </h2>
              <p className="text-xl text-gray-600 mb-10 max-w-2xl mx-auto">
                Administre e alugue suas quadras com facilidade. A solução completa e ideal para quadras modernas e apaixonados por esportes.
              </p>
              <div>
                  {
                    user ? (
                      <>
                      <Button asChild size="lg" className="bg-black text-white hover:bg-gray-800">
                        <Link href="/meus-agendamentos">Comece agora</Link>
                        </Button>
                      </>
                    ) : (
                      <>
                      <Button asChild size="lg" className="bg-black text-white hover:bg-gray-800">
                        <Link href="/login">Comece agora</Link>
                        </Button>
                      </>
                    )
                  }
              </div>
            </div>
          </section>

          <section id="features" className="py-20 sm:py-32 bg-gray-50">
            <div className="container mx-auto px-4 sm:px-6 lg:px-8">
              <h2 className="text-3xl font-bold mb-16 text-center text-gray-900">Funcionalidades</h2>
              <div className="grid grid-cols-1 md:grid-cols-3 gap-12">
                <FeatureCard
                  icon={<Calendar className="w-10 h-10 text-black" />}
                  title="Smart Booking"
                  description="Interface intuitiva para aluguéis fáceis e rápidos."
                />
                <FeatureCard
                  icon={<Clock className="w-10 h-10 text-black" />}
                  title="Acesso 24/7"
                  description="Gerencie suas reservas de qualquer hora, em qualquer dispositivo."
                />
                <FeatureCard
                  icon={<Users className="w-10 h-10 text-black" />}
                  title="Plataforma multi-usuarios"
                  description="Ideal para empresas com diferentes usuarios e quadras."
                />
              </div>
            </div>
          </section>

          <section id="about" className="py-20 sm:py-32">
            <div className="container mx-auto px-4 sm:px-6 lg:px-8">
              <h2 className="text-3xl font-bold mb-8 text-center text-gray-900">Sobre a Quaddra</h2>
              <p className="text-xl text-center max-w-3xl mx-auto text-gray-600 leading-relaxed">
                A Quaddra é uma plataforma inovadora dedicada ao gerenciamento e aluguel de quadras esportivas. Oferecemos uma solução moderna e eficiente para proprietários de quadras que desejam otimizar suas operações e atrair mais entusiastas do esporte. Com a Quaddra, a administração e o agendamento se tornam práticos, proporcionando uma experiência ágil e segura tanto para gestores quanto para usuários.
              </p>
            </div>
          </section>
        </main>

        <footer className="bg-gray-100 py-12">
          <div className="container mx-auto px-4 sm:px-6 lg:px-8 text-center">
            <p className="text-gray-600 mb-4">&copy; 2024 Quaddra. Todos os direitos reservados.</p>
            {/* <div className="space-x-4">
            <Link href="/terms" className="text-gray-600 hover:text-gray-900">Terms of Service</Link>
            <Link href="/privacy" className="text-gray-600 hover:text-gray-900">Privacy Policy</Link>
          </div> */}
          </div>
        </footer>
      </div>
    </>
  )
}

function FeatureCard(props: { icon: string, title: string, description: string }) {
  return (
    <div className="text-center">
      <div className="inline-block p-3 bg-white rounded-full shadow-md mb-4">
        {props.icon}
      </div>
      <h3 className="text-xl font-semibold mb-2 text-gray-900">{props.title}</h3>
      <p className="text-gray-600">{props.description}</p>
    </div>
  )
}