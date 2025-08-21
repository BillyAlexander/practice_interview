package com.evilla.test.solicitud.dtos;

import com.evilla.test.solicitud.enums.SolStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitudDto {
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotNull
	private SolStatus solStatus;
}
