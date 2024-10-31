import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Selenium_Test {

    //This will be Executed before any Test runs in the Class
    @Before
    public void CreateConnection(){
        System.out.println("Hello");
    }

    //Actual Test Method
    //In this we will add Selenium Code
    @Test
    public void Assert(){
        System.out.println("Into the Test");
        assertTrue(true);
    }

    //This will be Executed after any Test runs in the Class
    @After
    public void CloseConnection()
    {
        System.out.println("Hello End");
    }
}