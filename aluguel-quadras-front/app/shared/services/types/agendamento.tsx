export default interface Agendamento {
    idAgendamento: string
    idQuadra: string
    idUsuario: string
    inicioAgendamento: string
    fimAgendamento: string
    status: AgendamentoStatus
}

enum AgendamentoStatus {
    AGENDADO,
    CANCELADO
}