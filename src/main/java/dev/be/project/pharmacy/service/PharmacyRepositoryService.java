package dev.be.project.pharmacy.service;

import dev.be.project.pharmacy.entity.Pharmacy;
import dev.be.project.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRepositoryService {

    private final PharmacyRepository pharmacyRepository;

    @Transactional
    public void updateAddress(Long id, String address) {

        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)) {
            log.error("[주소 업데이트시 아이디 값을 찾을수 없음] : {}", id);
            return;
        }

        entity.changePharmacyAddress(address);

    }

    public void updateAddressWithoutTransaction(Long id, String address) {

        Pharmacy entity = pharmacyRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)) {
            log.error("[주소 업데이트시 아이디 값을 찾을수 없음] : {}", id);
            return;
        }

        entity.changePharmacyAddress(address);

    }

}
