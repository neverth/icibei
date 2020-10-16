package fun.neverth.icibei.organization.service.impl;

import fun.neverth.icibei.organization.entity.po.WordsCet4;
import fun.neverth.icibei.organization.service.WordsCet4Service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class WordsCet4ServiceImplTest {

    @Autowired
    WordsCet4Service wordsCet4Service;

    @Test
    void randomSelection() {
//        List<WordsCet4> wordsCet4s = wordsCet4Service.query(null, 1, 10, 0);
//        System.out.println();
    }
}