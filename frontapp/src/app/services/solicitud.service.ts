import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Solicitud, SolicitudPage } from '../models/solicitud';

@Injectable({
  providedIn: 'root'
})
export class SolicitudService {
  private apiUrl = 'http://localhost:8080/solicitudes';
  constructor(private http: HttpClient) { }


  getAll(page: number = 0, size: number = 10): Observable<SolicitudPage> {
    return this.http.get<SolicitudPage>(`${this.apiUrl}?page=${page}&size=${size}`);;
  }

  getById(id: String): Observable<Solicitud> {
    return this.http.get<Solicitud>(`${this.apiUrl}/${id}`);
  }

  create(solicitud: Solicitud): Observable<Solicitud> {
    return this.http.post<Solicitud>(this.apiUrl, solicitud);
  }

  update(solicitud: Solicitud): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${solicitud.solId}`, solicitud);
  }

  delete(id: String): Observable<{ message: string }> {
    return this.http.delete<{ message: string }>(`${this.apiUrl}/${id}`);
  }
}
