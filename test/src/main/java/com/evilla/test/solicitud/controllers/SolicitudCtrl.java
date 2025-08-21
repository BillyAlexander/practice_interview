package com.evilla.test.solicitud.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evilla.test.solicitud.dtos.SolicitudDto;
import com.evilla.test.solicitud.entity.Solicitud;
import com.evilla.test.solicitud.services.SolicitudService;
import com.evilla.test.solicitud.specifications.SpecificationTemplate;
import com.evilla.test.solicitud.validations.SolicitudValidator;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/solicitudes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SolicitudCtrl {

	@Autowired
	SolicitudService solicitudService;

	@Autowired
	SolicitudValidator solValidator;

	@GetMapping
	public ResponseEntity<Page<Solicitud>> getAllSol(SpecificationTemplate.SolicitudSpec spec,
			@PageableDefault(page = 0, size = 10, sort = "solId", direction = Sort.Direction.ASC) Pageable pageable) {
		log.info("params {}",spec);
		return ResponseEntity.status(HttpStatus.OK).body(solicitudService.findAll(spec,pageable));
	}

	@PostMapping
	public ResponseEntity<Object> saveSol(@RequestBody SolicitudDto solDto, Errors errors) {
		log.debug("POST save sol solDto received {}", solDto);
		solValidator.validate(solDto, errors);
		if (errors.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors());
		}

		var solModel = new Solicitud();
		BeanUtils.copyProperties(solDto, solModel);
		solModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		log.info("sol to save {}", solModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitudService.save(solModel));
	}

	@GetMapping("/{solId}")
	public ResponseEntity<Object> getOneSol(@PathVariable(value = "solId") UUID solId) {

		Optional<Solicitud> solModelOptional = solicitudService.findById(solId);
		if (!solModelOptional.isPresent())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sol not found.");
		else {
			return ResponseEntity.status(HttpStatus.OK).body(solModelOptional.get());
		}
	}

	@PutMapping("/{solId}")
	public ResponseEntity<Object> updateSol(@PathVariable(value = "solId") UUID solId,
			@RequestBody @Valid SolicitudDto solDto) {

		Optional<Solicitud> solModelOptional = solicitudService.findById(solId);
		if (!solModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("sol not found");
		}

		var solModel = solModelOptional.get();
		BeanUtils.copyProperties(solDto, solModel);
		solModel.setUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(solicitudService.save(solModel));
	}

	@DeleteMapping("/{solId}")
	public ResponseEntity<Object> deteleSol(@PathVariable(value = "solId") UUID solId) {
		Optional<Solicitud> solModelOptional = solicitudService.findById(solId);
		if (!solModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("sol not found");
		}

		return solicitudService.delete(solModelOptional.get())
				? ResponseEntity.ok(Map.of("message", "Solicitud eliminada correctamente"))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Solicitud no procesada"));
	}
	
	@GetMapping("/findbyprocname")
	public ResponseEntity<Object> getByNameSol(@RequestParam(value = "name") String name) {

		return ResponseEntity.status(HttpStatus.OK).body(solicitudService.findByProcName(name)); 
	}
}
