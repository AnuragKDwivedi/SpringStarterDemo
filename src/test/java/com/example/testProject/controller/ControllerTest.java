//package  com.example.testProject.controller;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import com.example.controller.MyController;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ControllerTest {
//
//    @InjectMocks
//    private MyController myController;
//
//    @Test
//    public void testGetAllEmployeesGroupByAddress() {
//        String mockedUsername = "Anurag";
//        myController = new MyController();
//        ReflectionTestUtils.setField(myController, "username", mockedUsername);
//
//        String result = myController.getAllEmployeesGroupByAddress();
//
//        assertEquals(mockedUsername.toUpperCase(), result);
//    }
//}
