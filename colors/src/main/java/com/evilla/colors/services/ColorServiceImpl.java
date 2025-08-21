package com.evilla.colors.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.evilla.colors.dtos.ColorDto;

import jakarta.annotation.PostConstruct;

@Service
public class ColorServiceImpl implements ColorService{

	private final List<ColorDto> colors = new ArrayList<>();
	
	@PostConstruct
    public void init() {
        colors.add(new ColorDto(1L, "Rojo"));
        colors.add(new ColorDto(2L, "Azul"));
        colors.add(new ColorDto(3L, "Verde"));
    }
	
	@Override
	public List<ColorDto> getAllColors() {
		return colors;
	}

	@Override
	public ColorDto getColorById(Long id) {
		return colors.stream()
	            .filter(c -> c.getIdColor().equals(id))
	            .findFirst()
	            .orElse(null);
	}

	@Override
	public ColorDto addColor(ColorDto dto) {
		dto.setIdColor((long)colors.size()+1);
		colors.add(dto);
		return dto;
	}

	@Override
	public boolean deleteColor(Long id) {
		return colors.removeIf(c -> c.getIdColor().equals(id));
	}

	@Override
	public ColorDto updateColor(ColorDto dto) {
		var upd=false;
		for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i).getIdColor().equals(dto.getIdColor())) {
                colors.set(i, dto);
                upd= true;
            }
        }
		
		return upd?dto:null;
	}

	@Override
	public ColorDto getColorByName(String name) {
		return colors.stream()
	            .filter(c -> c.getDescription().equals(name))
	            .findFirst()
	            .orElse(null);
	}

}
