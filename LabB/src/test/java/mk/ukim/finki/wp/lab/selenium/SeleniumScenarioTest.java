package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {


    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    private HtmlUnitDriver driver;


    private static User regularUser;
    private static User adminUser;
    private static Course course;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {

            course = courseService.save("test","test",Type.SUMMER);

            regularUser = userService.register(user, user, user, user, user, Role.ROLE_USER);
            adminUser = userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        CoursePage coursePage = CoursePage.to(this.driver);
        coursePage.assertElements( 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        coursePage = LoginPage.doLogin(this.driver,loginPage,adminUser.getUsername(),admin);
        coursePage.assertElements(1,1,1);

        loginPage = LoginPage.logout(this.driver);
        coursePage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);
        coursePage.assertElements( 0, 0, 0);


    }


}
