import { PageResponse } from "./pageresponse";

export enum SolStatus {
    CREATED = 'CREATED',
    IN_PROGRESS = 'IN_PROGRESS',
    FINISHED = 'FINISHED'
    // Agrega los estados 
  }

export interface Solicitud{
solId: string; // UUID como string
  name: string;
  description: string;
  creationDate?: string; // ISO 8601: 'yyyy-MM-ddTHH:mm:ssZ'
  solStatus: SolStatus;
  updateDate?: string; // Opcional
  color?: string;      // Opcional
}


export interface SolicitudPage extends PageResponse<Solicitud> {}