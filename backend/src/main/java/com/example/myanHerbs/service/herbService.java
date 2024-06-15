package com.example.myanHerbs.service;

import com.example.myanHerbs.dto.herbDto;

import java.util.List;

public interface herbService {

    boolean createHerbs(herbDto herbDto);
    List<herbDto> getAllHerbs();
    boolean updateHerbs(herbDto herbDto, Long id);
    boolean deleteHerbs(Long id);
    List<herbDto> getHerbsByName(String name);
    List<herbDto> readHerbByPagination(int pageNumber, int pageSize) throws IllegalAccessException;
}
