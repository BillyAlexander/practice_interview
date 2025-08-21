package com.evilla.test.solicitud.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evilla.test.solicitud.clients.ColorClient;
import com.evilla.test.solicitud.dtos.ColorDto;
import com.evilla.test.solicitud.entity.Solicitud;
import com.evilla.test.solicitud.enums.SolStatus;
import com.evilla.test.solicitud.projections.SolicitudProj;
import com.evilla.test.solicitud.repositories.SolicitudRepo;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class SolicitudServiceImpl implements SolicitudService {

	@Autowired
	SolicitudRepo solicitudRepo;

	@Autowired
	private ColorClient colorClient;

	@Override
	public boolean delete(Solicitud sol) {
		if (sol == null)
			return false;
		solicitudRepo.delete(sol);
		return true;

	}

	@Transactional
	@Override
	public Solicitud save(Solicitud sol) {

		if (sol == null)
			return null;

		try {
	        ColorDto color = colorClient.getColorById((long) SolStatus.FINISHED.ordinal());
	        sol.setColor(color.getDescription());
	    } catch (Exception ex) {
	        log.error("Error al obtener el color: {}", ex.getMessage());
	        throw new RuntimeException("No se pudo obtener el color, abortando la transacción");
	    }

		return solicitudRepo.save(sol);
	}

	@Override
	public Optional<Solicitud> findById(UUID solId) {
		return (solId == null) ? Optional.empty() : solicitudRepo.findById(solId);
	}

	@Override
	public Page<Solicitud> findAll(Specification<Solicitud> spec, Pageable pageable) {
		return solicitudRepo.findAll(spec, pageable);
	}

	@Override
	public List<SolicitudProj> findByProcName(String name) {
		
		return solicitudRepo.getSolicitudByProcName(name);
	}

}
