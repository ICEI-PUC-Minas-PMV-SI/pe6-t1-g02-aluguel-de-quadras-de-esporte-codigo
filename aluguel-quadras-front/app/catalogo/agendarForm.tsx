import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import React, { useEffect, useState } from "react";
import { TimePicker } from "../shared/custom-components/timePicker";
import { Calendar } from "@/components/ui/calendar";
import { PopoverContent, PopoverTrigger, Popover } from "@/components/ui/popover";
import { CalendarIcon } from "lucide-react";
import { format } from "date-fns";
import { Button } from "@/components/ui/button";
import { cn } from "@/lib/utils";
import { ptBR } from 'date-fns/locale'
import apiService from "../shared/services/api-service";
import Agendamento from "../shared/services/types/agendamento";
import { useToast } from "@/hooks/use-toast";
import { DialogClose } from "@radix-ui/react-dialog";
import { useAuth } from "../shared/auth/auth-context";

export default function AgendarForm(props: { quadra: any }) {
    const [dateOriginal, setDateOriginal] = useState(new Date())
    const [horaInicio, setHoraInicio] = useState(new Date())
    const [horaFim, setHoraFim] = useState(new Date())
    const { toast } = useToast()
    const { user } = useAuth()

    useEffect(() => {
        console.log(props)
    }, [])

    function agendar() {
        const dataInicio = new Date(dateOriginal);
        dataInicio.setHours(horaInicio.getHours());
        dataInicio.setMinutes(horaInicio.getMinutes());
        dataInicio.setSeconds(horaInicio.getSeconds());

        const dataFim = new Date(dateOriginal)
        dataFim.setHours(horaFim.getHours());
        dataFim.setMinutes(horaFim.getMinutes());
        dataFim.setSeconds(horaFim.getSeconds());

        apiService
            .criarAgendamento({ inicioAgendamento: dataInicio.toISOString(), fimAgendamento: dataFim.toISOString(), idQuadra: props.quadra?.id, idUsuario: user!.id })
            .then(() => {

                toast({
                    title: "Sucesso",
                    description: "Agendamento feito com sucesso."
                })
            })
    }

    return (
        <div className="py-4">

            <div className="columns-2" style={{ "display": "ruby" }}>
                <div>
                    <Label htmlFor='quadra'>Quadra</Label>
                    <Input id="quadra" disabled value={props.quadra?.nome}></Input>
                </div>
                <Popover>
                    <PopoverTrigger asChild>
                        <Button
                            variant={"outline"}
                            className={cn(
                                "w-[280px] justify-start text-left font-normal",
                                !dateOriginal && "text-muted-foreground"
                            )}
                        >
                            <CalendarIcon />
                            {dateOriginal ? format(dateOriginal, "PPP", { locale: ptBR }) : <span>Escolha uma data</span>}
                        </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0">
                        <Calendar
                            fromDate={Date.now()}
                            locale={ptBR}
                            mode="single"
                            selected={dateOriginal}
                            onSelect={setDateOriginal}
                            initialFocus
                        />
                    </PopoverContent>
                </Popover>
            </div>
            <div className="columns-2">

                <div>


                    <Label>Inicio do agendamento</Label>
                    <TimePicker
                        id="inicio"
                        date={horaInicio}
                        setDate={setHoraInicio}
                    ></TimePicker>

                </div>
                <div>

                    <Label>Fim do agendamento</Label>
                    <TimePicker
                        id="fim"
                        date={horaFim}
                        setDate={setHoraFim}
                    ></TimePicker>

                </div>
            </div>

            <DialogClose asChild>
                <Button onClick={agendar}>Agendar</Button>
            </DialogClose>

        </div>
    )
}