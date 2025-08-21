package com.evilla.colors.services;

import java.util.List;

import com.evilla.colors.dtos.ColorDto;

public interface ColorService {
	List<ColorDto> getAllColors();
	ColorDto getColorById(Long id);
	ColorDto getColorByName(String name);
	ColorDto addColor(ColorDto dto);
	boolean deleteColor(Long id);
	ColorDto updateColor(ColorDto dto);
}
