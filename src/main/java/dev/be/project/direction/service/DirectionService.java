package dev.be.project.direction.service;

import dev.be.project.api.dto.DocumentDto;
import dev.be.project.direction.entity.Direction;
import dev.be.project.pharmacy.dto.PharmacyDto;
import dev.be.project.pharmacy.service.PharmacySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DirectionService {

    // 약국 최대 검색 갯수
    private static final int MAX_SAERCH_COUNT = 3;

    // 반경 10km
    private static final double RADIUS_KM = 10.0;

    private final PharmacySearchService pharmacySearchService;

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

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    public List<Direction> buildDirectionList(DocumentDto documentDto) {

        // 약국 데이터 조회
        List<PharmacyDto> pharmacyDtos = pharmacySearchService.searchPharmacyDtoList();

        // 거리계산 알고리즘을 이용하여 고객과 약국 사이의 거리를 계산하고 sort


    }
}
