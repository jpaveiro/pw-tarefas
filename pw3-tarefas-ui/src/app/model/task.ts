export interface Task {
  id?: number;
  titulo: string;
  descricao: string;
  dataLimite: string; // ISO format
  status: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDA';
  responsavel: string;
}
