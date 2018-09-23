package com.pairing4good.petclinic.owner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void shouldSaveOwner() {

        assertEquals(0, ownerRepository.count());

        Owner entity = new Owner();
        entity.setFirstName("testFirstName");
        entity.setLastName("testLastName");
        entity.setAddress("test address");
        entity.setCity("testCity");
        entity.setTelephone("1234567890");

        ownerRepository.save(entity);

        assertEquals(1, ownerRepository.count());

    }
}
