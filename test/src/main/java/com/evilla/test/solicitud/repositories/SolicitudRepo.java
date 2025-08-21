package com.evilla.test.solicitud.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evilla.test.solicitud.entity.Solicitud;
import com.evilla.test.solicitud.projections.SolicitudProj;

@Repository
public interface SolicitudRepo extends JpaRepository<Solicitud, UUID>,JpaSpecificationExecutor<Solicitud> {

	//@Procedure(name = "Solicitud.sp_get_sol_by_name")
	@Query(value = "EXEC sp_get_sol_by_name :name", nativeQuery = true)
    List<SolicitudProj> getSolicitudByProcName(@Param("name") String name);
}
