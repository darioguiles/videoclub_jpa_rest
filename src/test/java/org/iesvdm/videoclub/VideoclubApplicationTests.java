package org.iesvdm.videoclub;

import jakarta.transaction.Transactional;
import org.iesvdm.videoclub.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VideoclubApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;
    @Test
    void contextLoads() {
    }
    @Test
    @Transactional //Es la anotación de Jakarta que nos inicia la transaccion
    void oneToMany(){
        //var nos ahorra un List<>
       var tutorialList = tutorialRepository.findAll();

       tutorialList.forEach(tutorial -> System.out.println(tutorial));

    }

}
