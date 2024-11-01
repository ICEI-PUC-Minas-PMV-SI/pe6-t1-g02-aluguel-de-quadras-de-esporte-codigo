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

export default function ReagendarForm(props: { quadra: any, agendamento: any }) {
    const [dateOriginal, setDateOriginal] = useState(new Date(props.agendamento.dataInicio))
    const [horaInicio, setHoraInicio] = useState(new Date(props.agendamento.dataInicio))
    const [horaFim, setHoraFim] = useState(new Date(props.agendamento.dataFim))

    useEffect(() => {
        console.log(props)
    }, [])

    function reagendar() {
        const dataInicio = new Date(dateOriginal);
        dataInicio.setHours(horaInicio.getHours());
        dataInicio.setMinutes(horaInicio.getMinutes());
        dataInicio.setSeconds(horaInicio.getSeconds());

        const dataFim = new Date(dateOriginal)
        dataFim.setHours(horaFim.getHours());
        dataFim.setMinutes(horaFim.getMinutes());
        dataFim.setSeconds(horaFim.getSeconds());

        apiService.reagendar(props.agendamento.idAgendamento, {dataInicio: dataInicio.toISOString(), dataFim: dataFim.toISOString()})
    }

    return (
        <div className="py-4">

            <div className="columns-2" style={{"display": "ruby"}}>
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

            <Button onClick={()=>{reagendar()}}>Reagendar</Button>
        </div>
    )
}