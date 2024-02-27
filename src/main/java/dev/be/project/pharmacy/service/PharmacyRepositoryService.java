package dev.be.project.pharmacy.service;

import dev.be.project.pharmacy.entity.Pharmacy;
import dev.be.project.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRepositoryService {

    private final PharmacyRepository pharmacyRepository;

    public void bar(List<Pharmacy> pharmacyList) {
        log.info("bar currenttranscationname : ", TransactionSynchronizationManager.getCurrentTransactionName());
        foo(pharmacyList); // 내부에서 메서드 호출시 @Transaction 붙어있지만 외부에는 없으면 트랜잭션 처리가 되지 않음.
    }

    public void foo(List<Pharmacy> pharmacyList) {
        log.info("bar currenttranscationname : ", TransactionSynchronizationManager.getCurrentTransactionName());
        pharmacyList.forEach(pharmacy -> {
            pharmacyRepository.save(pharmacy);
            throw new RuntimeException("error"); // 예외 발생 시 transaction에서 rollback 처리
        });
    }

    @Transactional(readOnly = true) // 읽기 전용으로 사용하면 JPA 스냅샷 저장 및 Dirty Checking 작업을 수행하지 않기에 성능적 이점이 있음.
    public void startReadOnlyMethod(Long id) {
        pharmacyRepository.findById(id).ifPresent(pharmacy -> {
            pharmacy.changePharmacyAddress("서울 특별시 광진구");
        });
    }

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

    @Transactional(readOnly = true)
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

}
