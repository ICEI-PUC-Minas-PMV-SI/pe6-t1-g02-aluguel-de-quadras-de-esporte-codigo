import axios from 'axios'
import Agendamento from './types/agendamento'
import LoginResponse from './types/loginResponse'
import { headers } from 'next/headers'

// Define the base URL for your API
const API_BASE_URL = 'http://localhost:8081'
const API_BASE_URL_USUARIOS = 'http://localhost:8080'
const API_BASE_URL_QUADRAS = 'http://localhost:8082'

// Create an axios instance with default config
const api = axios.create({
  baseURL: API_BASE_URL,
})

const apiUsuarios = axios.create({
  baseURL: API_BASE_URL_USUARIOS
})

const apiQuadras = axios.create({
  baseURL: API_BASE_URL_QUADRAS
})

// Add a request interceptor to include the auth token
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`
  }
  return config
}, (error) => {
  return Promise.reject(error)
})

apiUsuarios.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`
  }
  return config
}, (error) => {
  return Promise.reject(error)
})

// Add a response interceptor to handle common errors
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // Handle unauthorized error (e.g., redirect to login)
          console.error('Unauthorized access. Redirecting to login...')
          // You might want to use a more sophisticated method to handle redirects
          window.location.href = '/login'
          break
        case 403:
          console.error('Forbidden access.')
          break
        case 404:
          console.error('Resource not found.')
          break
        case 500:
          console.error('Internal server error.')
          break
        default:
          console.error('An error occurred:', error.response.data)
      }
    } else if (error.request) {
      console.error('No response received:', error.request)
    } else {
      console.error('Error setting up request:', error.message)
    }
    return Promise.reject(error)
  }
)

apiUsuarios.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // Handle unauthorized error (e.g., redirect to login)
          console.error('Unauthorized access. Redirecting to login...')
          // You might want to use a more sophisticated method to handle redirects
          window.location.href = '/login'
          break
        case 403:
          console.error('Forbidden access.')
          break
        case 404:
          console.error('Resource not found.')
          break
        case 500:
          console.error('Internal server error.')
          break
        default:
          console.error('An error occurred:', error.response.data)
      }
    } else if (error.request) {
      console.error('No response received:', error.request)
    } else {
      console.error('Error setting up request:', error.message)
    }
    return Promise.reject(error)
  }
)



const apiService = {

  login: (email: string, password: string) =>
    api.post<LoginResponse>('/api/v1/login', { email, password }),

  getAgendamentosByUser: (idUsuario: string) =>
    api.get<{ agendamentos: Agendamento[] }>(`/api/v1/agendamentos/usuario/${idUsuario}`, { headers: { 'Content-Type': 'application/json' }, data: null }),

  criarAgendamento: (agendamento: Omit<Agendamento, 'id'>) =>
    api.post<Agendamento>('/api/v1/agendamentos/', agendamento),

  reagendar: (id: string, reservation: Partial<Agendamento>) =>
    api.put<Agendamento>(`/api/v1/agendamentos/${id}`, reservation),

  cancelarAgendamento: (id: string) =>
    api.delete(`/api/v1/agendamentos/${id}`, { headers: { 'Content-Type': 'application/json' }, data: null }),

  getUser: (id: string) =>
    apiUsuarios.get(`/api/v1/usuarios/${id}`, { headers: { 'Content-Type': 'application/json' }, data: null }),

  editaUsuario: (userData: { senha: string; telefone: string; nome: string}, id: string) =>
    apiUsuarios.put(`/api/v1/usuarios/${id}`, userData) ,

  criarUsuario: (userData: { nome: string; telefone: string; email: string; senha: string; cpf?: string; cnpj?: string }) =>
    apiUsuarios.post('/api/v1/usuarios', userData),

  // Add more API methods as needed...
  buscarQuadras: (id: string) => apiQuadras.get(`/quadras/${id}`)
}

export default apiService