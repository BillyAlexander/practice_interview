package com.evilla.test.solicitud.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.evilla.test.solicitud.enums.SolStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "TB_SOLICITUD")
@NamedStoredProcedureQuery(
	    name = "Solicitud.sp_get_sol_by_name",
	    procedureName = "sp_get_sol_by_name",
	    parameters = {
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class)
	    },
	    resultClasses = Solicitud.class // o resultSetMappings si usas proyección
	)
public class Solicitud implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID solId;

	@Column(nullable = false, length = 150)
	private String name;
	@Column(nullable = false, length = 250)
	private String description;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime creationDate;
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SolStatus solStatus;
	
	@Column(nullable = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime updateDate;
	
	@Column(nullable = true)
	private String color; 

}
