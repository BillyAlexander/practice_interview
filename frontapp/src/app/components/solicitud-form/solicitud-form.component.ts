import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Solicitud, SolStatus } from 'src/app/models/solicitud';
import { SolicitudService } from 'src/app/services/solicitud.service';

@Component({
  selector: 'app-solicitud-form',
  templateUrl: './solicitud-form.component.html',
  styleUrls: ['./solicitud-form.component.scss']
})
export class SolicitudFormComponent implements OnInit {

  solicitud: Solicitud = {
    solId: '',
    name: '',
    description: '',
    solStatus: SolStatus.CREATED,
    color: ''
  };
  isEditMode: boolean = false;

  constructor(
    private solicitudService: SolicitudService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.solicitudService.getById(id).subscribe(data => this.solicitud = data);
    }
  }

  save(): void {
    if (this.isEditMode) {
      this.solicitudService.update(this.solicitud).subscribe(() => this.router.navigate(['/solicitudes']));
    } else {
      console.log(this.solicitud);
      this.solicitudService.create(this.solicitud).subscribe(() => this.router.navigate(['/solicitudes']));
    }
  }

  solStatusOptions = Object.values(SolStatus);

}
