import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Solicitud } from 'src/app/models/solicitud';
import { SolicitudService } from 'src/app/services/solicitud.service';

@Component({
  selector: 'app-solicitud-list',
  templateUrl: './solicitud-list.component.html',
  styleUrls: ['./solicitud-list.component.scss']
})
export class SolicitudListComponent {
  solicitudes: Solicitud[] = [];
totalElements = 0;
pageSize = 10;
currentPage = 0;

  constructor(private solicitudService: SolicitudService,private router: Router) {}

  ngOnInit(): void {
    this.loadSolicitudes();
  }

  loadSolicitudes(): void {
    this.solicitudService.getAll(this.currentPage, this.pageSize).subscribe({
      next: (data) => {
        this.solicitudes = data.content;
        this.totalElements = data.totalElements;
      },
      error: (err) => console.error('Error al cargar solicitudes', err)
    });
  }


  deleteSolicitud(id: string): void {
    if (confirm('¿Estás seguro de que deseas eliminar esta solicitud?')) {
      this.solicitudService.delete(id).subscribe({
        next: () => {
          this.solicitudes = this.solicitudes.filter(s => s.solId !== id);
          this.loadSolicitudes();
        },
        error: err => console.error('Error al eliminar solicitud', err)
      });
      
    }
  }

  irANueva(): void {
    this.router.navigate(['/solicitudes/nueva']);
  }

  editarSolicitud(id: string): void {
    this.router.navigate(['/solicitudes/editar', id]);
  }
}
