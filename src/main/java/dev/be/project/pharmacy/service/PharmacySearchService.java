package dev.be.project.pharmacy.service;

import dev.be.project.pharmacy.cache.PharmacyRedisTemplateService;
import dev.be.project.pharmacy.dto.PharmacyDto;
import dev.be.project.pharmacy.entity.Pharmacy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PharmacySearchService {

    private final PharmacyRepositoryService pharmacyRepositoryService;

    private final PharmacyRedisTemplateService pharmacyRedisTemplateService;


    public List<PharmacyDto> searchPharmacyDtoList() {

        // redis 사용
        List<PharmacyDto> pharmacyDtoList = pharmacyRedisTemplateService.findAll();
        if(!pharmacyDtoList.isEmpty()) return pharmacyDtoList;

        // db 사용
        return pharmacyRepositoryService.findAll()
                .stream()
                .map(this::convertToPharmacyDto)
                .collect(Collectors.toList());
    }

    private PharmacyDto convertToPharmacyDto(Pharmacy pharmacy) {
        return PharmacyDto.builder()
                .id(pharmacy.getId())
                .pharmacyAddress(pharmacy.getPharmacyAddress())
                .pharmacyName(pharmacy.getPharmacyName())
                .latitude(pharmacy.getLatitude())
                .longitude(pharmacy.getLongitude())
                .build();
    }
}
