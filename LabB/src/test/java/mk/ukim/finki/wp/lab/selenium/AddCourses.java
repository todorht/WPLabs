package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Type;
import org.assertj.core.util.diff.Delta;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddCourses extends AbstractPage {

    private WebElement name;
    private WebElement description;
    private WebElement type;
    private WebElement submit;


    public AddCourses(WebDriver driver) {
        super(driver);
    }

    public static CoursePage addCourse(WebDriver driver, String name, String description, String type) {
        get(driver, "/courses/add-form");
        AddCourses addCourses = PageFactory.initElements(driver, AddCourses.class);
        addCourses.name.sendKeys(name);
        addCourses.description.sendKeys(description);
        addCourses.type.click();
        //addCourses.type.findElement(By.xpath("//input[. ='" + type + "']")).click();

        addCourses.submit.click();
        return PageFactory.initElements(driver, CoursePage.class);
    }

}
