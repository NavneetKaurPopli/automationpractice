package com.example.springboot;

import com.example.springboot.data.StudentRepository;
import com.example.springboot.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DatabaseTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void createTest() {

    }

    @Test
    public void findByLastNameTest() {

    }

    @Test
    public void deleteTest() {

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void findAllActiveStudentsTest() {
    }

    @Test
    public void sortedFirstNamesTest() {
    }
}
