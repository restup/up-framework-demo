package com.github.restup.example;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.university.Course;
import com.github.restup.test.RestApiAssertions;
import com.github.restup.test.spring.AbstractMockMVCTest;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.filter.IColumnFilter;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT;

@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class CourseServiceJsonAPITest extends AbstractMockMVCTest {
    private static final String BASE = "/com/github/restup/example/CourseServiceJsonAPITest/";
    private static final String RESULTS = BASE + "results/";
    private static final String COURSE_XML = CourseServiceTest.COURSE_XML;

    public CourseServiceJsonAPITest() {
        super(Course.PLURAL_NAME, 1);
        jsonapi();
    }

    @Test
    @DatabaseSetup(COURSE_XML)
    public void getCourse() {
        api.get(2).ok();
    }

    @Test
    @DatabaseSetup(COURSE_XML)
    public void listCourses() {
        api.list().query("fields=name").ok();
    }

    @Test
    @DatabaseSetup(COURSE_XML)
    public void listPaged() {
        api.list().query("fields=name&limit=2&offset=1").ok();
    }

    @Test
    @DatabaseSetup(COURSE_XML)
    public void testRelationships() {
        // examples of fetching relationships between resources
        RestApiAssertions.Builder api = builder("/courses/{courseId}/university", 5);
        api.get().test("getCourseUniversity").ok();

        // and the reverse works as well
        api = builder("/universities/{universityId}/courses", 1);
        api.get().query("fields=name&limit=1&offset=2&sort=-name").test("getUniversityCourses").ok();
    }

    @Test
    @DatabaseSetup(COURSE_XML)
    @ExpectedDatabase(value = RESULTS + "createCourse.xml", table = "Course", assertionMode = NON_STRICT, columnFilters = IDFilter.class)
    public void createCourse() {
        api.add().ok();
    }

    @Test
    public void error400CreateErrors() {
        api.add().error400();
    }

    @Test
    public void error400PatchErrors() {
        api.patch().error400();
    }

    @Test
    @DatabaseSetup(COURSE_XML)
    @ExpectedDatabase(value = RESULTS + "patchCourse.xml", assertionMode = NON_STRICT)
    public void patchCourse() {
        api.patch().ok();
    }

    @Test
    @DatabaseSetup(COURSE_XML)
    @ExpectedDatabase(value = RESULTS + "deleteCourse.xml", assertionMode = NON_STRICT)
    public void testDelete() {
        api.delete().ok();
    }


    public static final class IDFilter implements IColumnFilter {
        @Override
        public boolean accept(String tableName, Column column) {
            return !column.getColumnName()
                    .equalsIgnoreCase("id");
        }
    }
}
