package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class HeaterDaoTest {

    @Autowired
    private HeaterDao heaterDao;

    @Test
    public void shouldFindAHeater(){
        Heater Heater =heaterDao.getOne(-10L);
        Assertions.assertThat(Heater.getName()).isEqualTo("Heater1");
        Assertions.assertThat(Heater.getHeaterStatus()).isEqualTo(HeaterStatus.ON);
    }
}
