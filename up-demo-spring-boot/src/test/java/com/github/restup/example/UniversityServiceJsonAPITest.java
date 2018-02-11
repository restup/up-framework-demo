package com.github.restup.example;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.university.University;
import com.github.restup.test.spring.AbstractMockMVCTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static com.github.restup.example.CourseServiceTest.COURSE_XML;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class UniversityServiceJsonAPITest extends AbstractMockMVCTest {

    public UniversityServiceJsonAPITest() {
        super(University.PLURAL_NAME, 1);
    }

    @Override
    @Before
    public void before() {
        super.before();
        api.jsonapi();
    }

    @Test
    @DatabaseSetup(COURSE_XML)
    public void listPaged() {
        api.list().ok();
    }

}
