import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Calendar, Clock, Users } from "lucide-react"

export default function LandingPage() {
  return (
    <div className="min-h-screen bg-white">
      <header className="py-6 px-4 sm:px-6 lg:px-8">
        <div className="container mx-auto flex justify-between items-center">
          <h1 className="text-2xl font-bold text-gray-900">CourtMaster</h1>
          <nav className="hidden md:flex space-x-8">
            <Link href="#features" className="text-gray-600 hover:text-gray-900">Features</Link>
            <Link href="#about" className="text-gray-600 hover:text-gray-900">About</Link>
            <Link href="/login" className="text-gray-600 hover:text-gray-900">Login</Link>
          </nav>
          <Button variant="outline" className="md:hidden">Menu</Button>
        </div>
      </header>

      <main>
        <section className="py-20 sm:py-32">
          <div className="container mx-auto px-4 sm:px-6 lg:px-8 text-center">
            <h2 className="text-4xl sm:text-5xl font-extrabold text-gray-900 mb-6">
              Simplify Court Scheduling
            </h2>
            <p className="text-xl text-gray-600 mb-10 max-w-2xl mx-auto">
              Effortlessly manage and book sports courts. The ultimate solution for modern facilities and sports enthusiasts.
            </p>
            <Button asChild size="lg" className="bg-black text-white hover:bg-gray-800">
              <Link href="/signup">Get Started</Link>
            </Button>
          </div>
        </section>

        <section id="features" className="py-20 sm:py-32 bg-gray-50">
          <div className="container mx-auto px-4 sm:px-6 lg:px-8">
            <h2 className="text-3xl font-bold mb-16 text-center text-gray-900">Key Features</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-12">
              <FeatureCard 
                icon={<Calendar className="w-10 h-10 text-black" />}
                title="Smart Booking"
                description="Intuitive interface for quick and easy court reservations."
              />
              <FeatureCard 
                icon={<Clock className="w-10 h-10 text-black" />}
                title="24/7 Accessibility"
                description="Manage bookings anytime, from any device."
              />
              <FeatureCard 
                icon={<Users className="w-10 h-10 text-black" />}
                title="Multi-user Platform"
                description="Ideal for facilities with various courts and user types."
              />
            </div>
          </div>
        </section>

        <section id="about" className="py-20 sm:py-32">
          <div className="container mx-auto px-4 sm:px-6 lg:px-8">
            <h2 className="text-3xl font-bold mb-8 text-center text-gray-900">About CourtMaster</h2>
            <p className="text-xl text-center max-w-3xl mx-auto text-gray-600 leading-relaxed">
              Born from a passion for sports and technology, CourtMaster revolutionizes court management. 
              We empower facility managers and sports enthusiasts to focus on what truly matters - the game itself.
            </p>
          </div>
        </section>
      </main>

      <footer className="bg-gray-100 py-12">
        <div className="container mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <p className="text-gray-600 mb-4">&copy; 2024 CourtMaster. All rights reserved.</p>
          <div className="space-x-4">
            <Link href="/terms" className="text-gray-600 hover:text-gray-900">Terms of Service</Link>
            <Link href="/privacy" className="text-gray-600 hover:text-gray-900">Privacy Policy</Link>
          </div>
        </div>
      </footer>
    </div>
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