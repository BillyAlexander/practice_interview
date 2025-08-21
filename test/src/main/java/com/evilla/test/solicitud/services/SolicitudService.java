package com.evilla.test.solicitud.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.evilla.test.solicitud.entity.Solicitud;
import com.evilla.test.solicitud.projections.SolicitudProj;

public interface SolicitudService {
	boolean delete(Solicitud sol);

    Solicitud save(Solicitud sol);

	Optional<Solicitud> findById(UUID solId);
	
	Page<Solicitud>findAll(Specification<Solicitud>  spec,Pageable pageable);
	
	List<SolicitudProj> findByProcName(String name);

}
