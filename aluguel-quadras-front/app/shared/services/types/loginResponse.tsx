import Usuario from "./usuario"

export default interface LoginResponse {
    token: string
    expiresIn: number
    user: Omit<Usuario, 'telefone' | 'dataCriacao' | 'dataUltimaAtualizacao' | 'dataDelecao'> 
}