export default interface Agendamento {
    idAgendamento: string
    idQuadra: string
    idUsuario: string
    dataInicio: string
    dataFim: string
    status: AgendamentoStatus
}

enum AgendamentoStatus {
    AGENDADO,
    CANCELADO
}