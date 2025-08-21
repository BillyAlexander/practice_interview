package com.evilla.colors.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evilla.colors.dtos.ColorDto;
import com.evilla.colors.services.ColorService;

@RestController
@RequestMapping("/colors")
public class ColorCtrl {

	@Autowired
	private ColorService serviceColor;

	@GetMapping
	public ResponseEntity<List<ColorDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(serviceColor.getAllColors());
	}

	@PostMapping
	public ResponseEntity<ColorDto> create(@RequestBody ColorDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceColor.addColor(dto));
	}

	@PutMapping
	public ResponseEntity<Object> update(@RequestBody ColorDto dto) {

		return serviceColor.updateColor(dto) == null
				? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sol not found.")
				: ResponseEntity.status(HttpStatus.OK).body(serviceColor.addColor(dto));
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return serviceColor.deleteColor(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ColorDto> getById(@PathVariable Long id) {
		if( id==null) {
			return ResponseEntity.badRequest().build();
		}
        ColorDto dto = serviceColor.getColorById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }
	
	@GetMapping(value = "find")
    public ResponseEntity<Object> getByName(@RequestParam(required = false) String nameparam) {
		if( nameparam==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("name is null.");
		}
        ColorDto dto = serviceColor.getColorByName(nameparam);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

}
