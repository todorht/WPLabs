package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exception.InvalidNameAndSurnameException;
import mk.ukim.finki.wp.lab.model.exception.InvalidUsernameOrPasswordException;
import mk.ukim.finki.wp.lab.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.CoursesRepositoryJpa;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.impl.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentSaveTest {

    @Mock
    private StudentRepositoryJpa studentRepository;

    @Mock
    private CoursesRepositoryJpa coursesRepository;

    private StudentService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Student student = new Student("username","password","name","surname");
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        this.service=Mockito.spy(new StudentServiceImpl(this.studentRepository, this.coursesRepository));
    }

    @Test
    public void testSuccessSave(){
        Student student = this.service.save("username","password","name","surname");

        Mockito.verify(this.service).save("username","password","name","surname");

        Assert.assertNotNull("User is null", student);
        Assert.assertEquals("name do not mach", "name", student.getName());
        Assert.assertEquals("surname do not mach", "surname", student.getSurname());
        Assert.assertEquals("password do not mach", "password", student.getPassword());
        Assert.assertEquals("username do not mach", "username", student.getUsername());

    }

    @Test
    public void testNullUsername(){
        Assert.assertThrows("InvalidUsernameOrPasswordException expected"
                , InvalidUsernameOrPasswordException.class
                ,()-> this.service.save(null,"password","name","surname"));
        Mockito.verify(this.service).save(null,"password","name","surname");
    }

    @Test
    public void testEmptyUsername(){
        String username = "";
        Assert.assertThrows("InvalidUsernameOrPasswordException expected"
                , InvalidUsernameOrPasswordException.class
                ,()-> this.service.save(username,"password","name","surname"));
        Mockito.verify(this.service).save(username,"password","name","surname");
    }
    @Test
    public void testNullPassword(){
        Assert.assertThrows("InvalidUsernameOrPasswordException expected"
                , InvalidUsernameOrPasswordException.class
                ,()-> this.service.save("username",null,"name","surname"));
        Mockito.verify(this.service).save("username",null,"name","surname");
    }

    @Test
    public void testEmptyPassword(){
        String password = "";
        Assert.assertThrows("InvalidUsernameOrPasswordException expected"
                , InvalidUsernameOrPasswordException.class
                ,()-> this.service.save("username",password,"name","surname"));
        Mockito.verify(this.service).save("username",password,"name","surname");
    }
    @Test
    public void testNullName(){
        Assert.assertThrows("InvalidNameAndSurnameException expected"
                , InvalidNameAndSurnameException.class
                ,()-> this.service.save("username","password",null,"surname"));
        Mockito.verify(this.service).save("username","password",null,"surname");
    }

    @Test
    public void testEmptyName(){
        String name = "";
        Assert.assertThrows("InvalidNameAndSurnameException expected"
                , InvalidNameAndSurnameException.class
                ,()-> this.service.save("username","password",name,"surname"));
        Mockito.verify(this.service).save("username","password",name,"surname");
    }

    @Test
    public void testNullSurname(){
        Assert.assertThrows("InvalidNameAndSurnameException expected"
                , InvalidNameAndSurnameException.class
                ,()-> this.service.save("username","password","name",null));
        Mockito.verify(this.service).save("username","password","name",null);
    }

    @Test
    public void testEmptySurname(){
        String surname = "";
        Assert.assertThrows("InvalidNameAndSurnameException expected"
                , InvalidNameAndSurnameException.class
                ,()-> this.service.save("username","password","name",surname));
        Mockito.verify(this.service).save("username","password","name",surname);
    }

    @Test
    public void testDuplicateUsername() {
        Student student = new Student("username", "password", "name", "surename");
        Mockito.when(this.studentRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(student));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.service.save(username, "password",  "name", "surename"));
        Mockito.verify(this.service).save(username, "password", "name", "surename");
    }


}
