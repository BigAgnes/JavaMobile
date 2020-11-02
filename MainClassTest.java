import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{
    @Test
    public void testGetLocalNumber()
    {
        Assert.assertTrue( "This value is not 14!",getLocalNumber()==14);
    }
}
