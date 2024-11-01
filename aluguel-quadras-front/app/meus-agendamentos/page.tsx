'use client'

import { useState, useEffect } from 'react'
import { useRouter } from 'next/navigation'
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { Calendar, Clock, MapPin, Edit, Trash2, PlusCircle } from "lucide-react"
import { Navbar } from "../shared/custom-components/navbar"
import { AuthProvider, useAuth } from '../shared/auth/auth-context'
import apiService from '../shared/services/api-service'
import Link from 'next/link'
import { Badge } from '@/components/ui/badge'
import Agendamento from '../shared/services/types/agendamento'
import isoDateFormatter from '../shared/helpers/isoDateFormatter'
import { Label } from '@/components/ui/label'
import { Input } from '@/components/ui/input'
import ReagendarForm from './reagendarForm'

// Mock data for scheduled courts (empty for demonstration)
// const scheduledCourts = [
//   { id: 1, courtName: 'Tennis Court A', date: '2024-03-15', time: '14:00-15:00', location: 'Main Complex' },
//   { id: 2, courtName: 'Basketball Court 1', date: '2024-03-16', time: '10:00-11:00', location: 'Sports Center' },
//   { id: 3, courtName: 'Squash Court B', date: '2024-03-17', time: '18:00-19:00', location: 'Fitness Club' },
// ]

export default function CourtManagementWrapper() {

  return (
    <AuthProvider>
      <CourtManagement></CourtManagement>
    </AuthProvider>
  )
}



function CourtManagement() {
  const [agendamentos, setAgendamentos] = useState([])
  const [selectedCourt, setSelectedCourt] = useState(null)
  const [loading, setLoading] = useState(true)
  const [quadras, setQuadras] = useState(new Map())
  const user = useAuth().user
  const router = useRouter()

  function cancelarAgendamento(id: string) {
    console.log("cancelando agendamento")
    apiService.cancelarAgendamento(id).then(r=> {
      let newAgendamentos: Agendamento[] = [...agendamentos]
      newAgendamentos.forEach(agendamento=> {
        if(agendamento.idAgendamento === id) {
          console.log("agendamento cancelado")
        agendamento.status = "CANCELADO"
      }})
      setAgendamentos(newAgendamentos)
    })
  }

  useEffect(()=>{
    console.log("chamando quadras")
    agendamentos.map((agendamento)=>{
      console.log(agendamento)
      if(!quadras.get(agendamento.idQuadra)) {
        apiService.buscarQuadras(agendamento.idQuadra)
        .then(quadra => {
          quadras.set(quadra.data.id, quadra.data);
          console.log(quadras)
        })
      }

    })
  }, [agendamentos])

  useEffect(() => {
    if (typeof window !== 'undefined') {
      if (!user) {
        router.push('/login')
      } else {
        apiService.getAgendamentosByUser(user.id).then(r => {
          setAgendamentos(r.data.agendamentos)
          setLoading(false)
        })

      }
    }
  }, [])


  if (loading) {
    return <div className="min-h-screen flex items-center justify-center">Loading...</div>
  }

  return (

    <AuthProvider>
      <Navbar />
      <div className="min-h-screen bg-gray-50 py-8">
        <div className="container mx-auto px-4">
          <h1 className="text-3xl font-bold mb-8 text-center text-gray-900">Minhas quadras agendadas</h1>
          <Card>
            <CardHeader className="flex flex-row items-center justify-between">
              <CardTitle>Agendamentos ({agendamentos.length})</CardTitle>
              <Button className="bg-black text-white hover:bg-gray-800">
                <PlusCircle className="mr-2 h-4 w-4" />
                <Link href="/quadras">
                  Escolha uma quadra
                </Link>

              </Button>
            </CardHeader>
            <CardContent>
              {agendamentos.length > 0 ? (
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead>Quadra</TableHead>
                      <TableHead>Data</TableHead>
                      <TableHead>Horário</TableHead>
                      <TableHead>Local</TableHead>
                      <TableHead>Status</TableHead>
                      <TableHead>Ações</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {agendamentos.map((agendamento) => (
                      <TableRow key={agendamento.idAgendamento}>
                        <TableCell className="font-medium">{quadras.get(parseInt(agendamento.idQuadra))?.nome || `-`}</TableCell>
                        <TableCell>{isoDateFormatter.formatDate(agendamento.dataInicio)}</TableCell>
                        <TableCell>{isoDateFormatter.formatTime(agendamento.dataInicio)+ " - " + isoDateFormatter.formatTime(agendamento.dataFim)}</TableCell>
                        <TableCell className="font-medium">{quadras.get(parseInt(agendamento.idQuadra))?.localizacao || `-`}</TableCell>
                        <TableCell>
                          <Badge variant={agendamento.status === 'CANCELADO' ? "destructive" : "default"}>{agendamento.status}</Badge>
                        </TableCell>
                        <TableCell>
                          {
                            agendamento.status !== "CANCELADO" ? (
                              <div className="flex space-x-2">
                                <Dialog>
                                  <DialogTrigger asChild>
                                    <Button variant="outline" size="icon" onClick={() => 
                                       setSelectedCourt(quadras.get(parseInt(agendamento.idQuadra)))
                                      }>
                                      <Edit className="h-4 w-4" />
                                    </Button>
                                  </DialogTrigger>
                                  <DialogContent>
                                    <DialogHeader>
                                      <DialogTitle>Reagendar quadra</DialogTitle>
                                    </DialogHeader>
                                      <ReagendarForm quadra={selectedCourt} agendamento={agendamento}>

                                      </ReagendarForm>
                                  </DialogContent>
                                </Dialog>
                                <Button variant="destructive" size="icon" onClick={()=>cancelarAgendamento(agendamento.idAgendamento)}>
                                  <Trash2 className="h-4 w-4" />
                                </Button>
                              </div>
                            ) : ('N/A')
                          }
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              ) : (
                <div className="text-center py-12">
                  <Calendar className="mx-auto h-12 w-12 text-gray-400" />
                  <h3 className="mt-2 text-sm font-semibold text-gray-900">Sem agendamentos</h3>
                  <p className="mt-1 text-sm text-gray-500">Escolha uma quadra e faca sua reserva.</p>
                  <div className="mt-6">
                    <Button className="bg-black text-white hover:bg-gray-800">
                      <PlusCircle className="mr-2 h-4 w-4" />
                      <Link href="/quadras"> Escolha uma quadra </Link>
                    </Button>
                  </div>
                </div>
              )}
            </CardContent>
          </Card>

        </div>
      </div>

    </AuthProvider>
  )
}