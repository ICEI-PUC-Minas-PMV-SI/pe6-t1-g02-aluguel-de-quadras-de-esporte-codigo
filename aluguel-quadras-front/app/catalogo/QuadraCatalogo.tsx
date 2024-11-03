"use client";

import { useState } from 'react';
import { Button } from "@/components/ui/button";
import { Navbar } from "../shared/custom-components/navbar";

export default function QuadraCatalogo() {
  const [quadras] = useState([
    { id: 1, nome: 'Quadra de Futebol', localizacao: 'A1', tipo: 'Futebol', precoPorHora: 100, imagem: '/images/quadra1.jpg' },
    { id: 2, nome: 'Quadra Fechada', localizacao: 'A3', tipo: 'Basquete', precoPorHora: 80, imagem: '/images/quadra2.jpg' },
    { id: 3, nome: 'Quadra de Basquete', localizacao: 'Bairro Norte', tipo: 'Vôlei', precoPorHora: 90, imagem: '/images/quadra3.jpg' },
    { id: 4, nome: 'Quadra de Areia', localizacao: 'Bairro Leste', tipo: 'Tênis', precoPorHora: 120, imagem: '/images/quadra4.jpg' },
    { id: 5, nome: 'Quadra de Tênis', localizacao: 'Bairro Oeste', tipo: 'Futsal', precoPorHora: 85, imagem: '/images/quadra5.jpg' },
    { id: 6, nome: 'Quadra Aberta', localizacao: 'Praia Central', tipo: 'Beach Tennis', precoPorHora: 110, imagem: '/images/quadra6.jpg' },
  ]);

  const [isModalOpen, setModalOpen] = useState(false);
  const [isConfirmationModalOpen, setConfirmationModalOpen] = useState(false);
  const [selectedQuadra, setSelectedQuadra] = useState(null);
  const [selectedDate, setSelectedDate] = useState("");
  const [selectedTime, setSelectedTime] = useState("");

  const openModal = (quadra) => {
    setSelectedQuadra(quadra);
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
    setSelectedDate("");
    setSelectedTime("");
  };

  const handleConfirm = () => {
    closeModal();
    setConfirmationModalOpen(true); // Abre o modal de confirmação
  };

  const closeConfirmationModal = () => {
    setConfirmationModalOpen(false);
  };

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
              <Button onClick={() => openModal(quadra)}>
                Reservar
              </Button>
            </div>
          ))}
        </div>
        
        {/* Modal de Agendamento */}
        {isModalOpen && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white p-8 rounded-lg max-w-sm w-full">
              <h2 className="text-xl font-bold mb-4">Selecione a data e hora</h2>
              
              {/* Seleção de Data */}
              <label className="block mb-2 font-semibold">Selecione o Dia:</label>
              <input
                type="date"
                className="border rounded p-2 w-full mb-4"
                value={selectedDate}
                onChange={(e) => setSelectedDate(e.target.value)}
              />

              {/* Seleção de Hora */}
              <label className="block mb-2 font-semibold">Selecione a Hora:</label>
              <input
                type="time"
                className="border rounded p-2 w-full mb-4"
                value={selectedTime}
                onChange={(e) => setSelectedTime(e.target.value)}
              />

              <div className="flex justify-end space-x-2">
                <Button onClick={closeModal}>Cancelar</Button>
                <Button onClick={handleConfirm} disabled={!selectedDate || !selectedTime}>
                  Confirmar
                </Button>
              </div>
            </div>
          </div>
        )}

        {/* Modal de Confirmação */}
        {isConfirmationModalOpen && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white p-8 rounded-lg max-w-sm w-full text-center">
              <h2 className="text-2xl font-bold text-gray-800 mb-2">Agendamento feito com sucesso</h2>
              <p className="text-gray-600 mb-6">Te esperamos no dia e horário agendados!</p>
              <Button onClick={closeConfirmationModal}>Fechar</Button>
            </div>
          </div>
        )}
      </div>
    </>
  );
}
