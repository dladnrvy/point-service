package com.example.pointservice.repository;

import com.example.pointservice.domain.Barcode;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
class BarcodeRepositoryTest {

    @Autowired
    private BarcodeRepository barcodeRepository;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    public void 바코드_저장_테스트(){
        // given
        Barcode barcode = new Barcode();
        barcode.setUserId(123456L);
        barcode.setBarcode("123456789");

        // when
        Barcode saveBarcode = barcodeRepository.save(barcode);
        Long saveId = saveBarcode.getId();

        Optional<Barcode> findId = barcodeRepository.findById(saveId);
        Long findUserId = findId.get().getUserId();
        String findBarCode = findId.get().getBarcode();

        // then
        Assertions.assertThat(findUserId).isEqualTo(barcode.getUserId());
        Assertions.assertThat(findBarCode).isEqualTo(barcode.getBarcode());
    }

    @Test
    public void 바코드_길이_오류_테스트(){
        // given
        Barcode barcode = new Barcode();
        //자릿수 테스트
        barcode.setBarcode("12345678900");
        barcode.setUserId(12345678900L);

        Assertions.assertThatThrownBy(()-> barcodeRepository.save(barcode)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void 바코드_중복_테스트_오류체크() {
        Barcode barcode1, barcode2;

        barcode1 = new Barcode(1L,123456789L,"5767844471");
        barcode2 = new Barcode(2L,123456788L,"5767844471");
        barcodeRepository.save(barcode1);
        Assertions.assertThatThrownBy(()-> barcodeRepository.save(barcode2)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void 회원아이디_중복_테스트_오류체크() {
        Barcode barcode1, barcode2;

        barcode1 = new Barcode(1L,123456788L,"5767844471");
        barcode2 = new Barcode(2L,123456788L,"5767844472");
        barcodeRepository.save(barcode1);
        Assertions.assertThatThrownBy(()-> barcodeRepository.save(barcode2)).isInstanceOf(DataIntegrityViolationException.class);
    }



}