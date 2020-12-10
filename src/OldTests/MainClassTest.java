package OldTests;

import OldTests.MainClass;
import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber()
    {
        Assert.assertTrue( "This value is not 14!",getLocalNumber()==14);
    }

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue( "Value less than 45",getClassNumber()>45);
    }

    @Test
    public void testGetClassString()
    {
        String actual = getClassString();
        //String expected = "Hello";
        //String expectedTwo = "hello";
        Assert.assertTrue("In message not have Hello!", actual.contains("Hello") || actual.contains("hello"));
    }
}
