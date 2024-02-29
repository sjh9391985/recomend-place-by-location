package dev.be.project.pharmacy.service;

import dev.be.project.api.dto.DocumentDto;
import dev.be.project.api.dto.KakaoApiResponseDto;
import dev.be.project.api.service.KakaoAddressSearchService;
import dev.be.project.direction.entity.Direction;
import dev.be.project.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPharmacyList(String address) {

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if(Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentDtoList())) {
            return;
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentDtoList().get(0);
        List<Direction> directionList = directionService.buildDirectionList(documentDto);

        directionService.saveAll(directionList);

    }

}
