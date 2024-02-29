package dev.be.project.direction.service;

import dev.be.project.api.dto.DocumentDto;
import dev.be.project.api.service.KakaoCategorySearchService;
import dev.be.project.direction.entity.Direction;
import dev.be.project.direction.repository.DirectionRepository;
import dev.be.project.pharmacy.dto.PharmacyDto;
import dev.be.project.pharmacy.service.PharmacySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DirectionService {

    // 약국 최대 검색 갯수
    private static final int MAX_SAERCH_COUNT = 3;

    // 반경 10km
    private static final double RADIUS_KM = 10.0;

    private final PharmacySearchService pharmacySearchService;
    private final DirectionRepository directionRepository;
    private final KakaoCategorySearchService kakaoCategorySearchService;

    @Transactional
    public List<Direction> saveAll(List<Direction> directionList) {
        if (CollectionUtils.isEmpty(directionList)) return Collections.emptyList();
        return directionRepository.saveAll(directionList);

    }

    public List<Direction> buildDirectionListByCategoryApi(DocumentDto inputDocumentDto) {
        if (Objects.isNull(inputDocumentDto)) return Collections.emptyList();

        // 약국 데이터 조회 & 거리계산 알고리즘을 이용하여 고객과 약국 사이의 거리를 계산하고 sort
        return kakaoCategorySearchService.requestPharmacyCategorySearch(inputDocumentDto.getLatitude(), inputDocumentDto.getLongitude(), RADIUS_KM)
                .getDocumentDtoList()
                .stream().map(resultDocumentDto -> Direction.builder()
                        .inputAddress(inputDocumentDto.getAddressName())
                        .inputLatitude(inputDocumentDto.getLatitude())
                        .inputLongtitude(inputDocumentDto.getLongitude())
                        .targetPharmacyName(resultDocumentDto.getPlaceName())
                        .targerAddress(resultDocumentDto.getAddressName())
                        .targetLatitude(resultDocumentDto.getLatitude())
                        .targetLongtitude(resultDocumentDto.getLongitude())
                        .distance(
                                resultDocumentDto.getDistance() * 0.001
                        )
                        .build())
                .limit(MAX_SAERCH_COUNT)
                .collect(Collectors.toList());


    }

    // Haversine formula algorithm
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 사용자 위도, 경도
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);

        // 약국의 위도, 경도
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        double earthRadius = 6371; // 킬로미터
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}
