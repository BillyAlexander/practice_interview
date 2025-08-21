package com.evilla.test.solicitud.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.evilla.test.solicitud.entity.Solicitud;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.domain.LessThan;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
	@And({
        @Spec(path = "solStatus", spec = Equal.class),
        @Spec(path = "name", spec = LikeIgnoreCase.class),
        @Spec(path = "description", spec = LikeIgnoreCase.class),
        @Spec(path = "creationDate", params = "createdAfter", spec = GreaterThan.class),
        @Spec(path = "updateDate", params = "updatedBefore", spec = LessThan.class)
    })
    public interface SolicitudSpec extends Specification<Solicitud> {
    }
}
