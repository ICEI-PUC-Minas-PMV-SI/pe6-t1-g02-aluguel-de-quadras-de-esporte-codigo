"use client";

import { useState } from 'react';
import { Button } from "@/components/ui/button";
import { Navbar } from "../shared/custom-components/navbar";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from '@/components/ui/dialog';
import AgendarForm from './agendarForm';
import { useAuth } from '../shared/auth/auth-context';
import apiService from '../shared/services/api-service';

export default function QuadraCatalogo() {
  const [quadras] = useState(apiService.buscarQuadras());
  const {user} = useAuth()

  return (
    <>
      <Navbar />
      <div className="max-w-4xl mx-auto mt-10 space-y-4">
        <h2 className="text-2xl font-bold text-center">Catálogo de Quadras</h2>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {quadras.map((quadra) => (
            <div key={quadra.id} className="border p-4 rounded-lg shadow-md">
              <img src={quadra.imagem} alt={`Imagem de ${quadra.nome}`} className="w-full h-40 object-cover rounded-md mb-4" />
              <h3 className="text-lg font-semibold">{quadra.nome}</h3>
              <p><strong>Localização:</strong> {quadra.localizacao}</p>
              <p><strong>Tipo:</strong> {quadra.tipo}</p>
              <p><strong>Preço por hora:</strong> R${quadra.precoPorHora}</p>
              {user?               <Dialog>
                <DialogTrigger asChild>
                  <Button>
                    Reservar
                  </Button>
                </DialogTrigger>
                <DialogContent>
                  <DialogHeader>
                    <DialogTitle>Agendar quadra</DialogTitle>
                  </DialogHeader>
                  <AgendarForm quadra={quadra}>

                  </AgendarForm>
                </DialogContent>
              </Dialog> : <></>}
            </div>
          ))}
        </div>



      </div>
    </>
  );
}
