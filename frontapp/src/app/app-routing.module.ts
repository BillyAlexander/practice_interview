import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SolicitudListComponent } from './components/solicitud-list/solicitud-list.component';
import { SolicitudFormComponent } from './components/solicitud-form/solicitud-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'solicitudes', pathMatch: 'full' },
  { path: 'solicitudes', component: SolicitudListComponent },
  { path: 'solicitudes/nueva', component: SolicitudFormComponent },
  { path: 'solicitudes/editar/:id', component: SolicitudFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
