package com.example.myanHerbs.service.serviceImp;

import com.example.myanHerbs.dto.herbDto;
import com.example.myanHerbs.model.herb;
import com.example.myanHerbs.repository.herbRepository;
import com.example.myanHerbs.service.herbService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class herbServiceImp implements herbService {

    @Autowired
    private final herbRepository herbRepository;
    private final ModelMapper modelMapper;

    public herbServiceImp(com.example.myanHerbs.repository.herbRepository herbRepository, ModelMapper modelMapper) {
        this.herbRepository = herbRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createHerbs(herbDto herbDto) {
        herb h=new herb();
        h.setName(herbDto.getName());
        h.setBackground(herbDto.getBackground());
        h.setDetail(herbDto.getDetail());
        h.setCode(herbDto.getCode());

        herbRepository.save(h);
        return false;
    }

    @Override
    public List<herbDto> getAllHerbs() {
        try {
            List<herb> herbs = herbRepository.findAll();
            int numberOfHerbs = herbs.size();
            return herbs.stream().map(herb -> modelMapper.map(herb, herbDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve herbs list", e);
        }
    }

    @Override
    public boolean updateHerbs(herbDto herbDto, Long id) {
        herb existingHerb = herbRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("herb not found with ID: " + id));

        modelMapper.map(herbDto, existingHerb);
        herbRepository.save(existingHerb);
        return false;
    }

    @Override
    public boolean deleteHerbs(Long id) {
        Optional<herb> herbOptional = herbRepository.findById(id);
        if (herbOptional.isEmpty()) {
            System.out.println("herb not found");
            return false;
        }
        herb toDeleteHerb = herbOptional.get();
        herbRepository.delete(toDeleteHerb);
        return false;
    }

    @Override
    public List<herbDto> getHerbsByName(String name) {
        List<herb> herb = herbRepository.findByNameContaining(name);
        return herb.stream()
                .map(herb1 -> modelMapper.map(herb, herbDto.class))
                .collect(Collectors.toList());
    }
}
