package com.robiclabs.taskv2.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class ReportRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
}
