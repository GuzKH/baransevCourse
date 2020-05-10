import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class PointTest {

    @Test
    public void testOne() {
        //given
        Point p1 = new Point(3,4);
        Point p2 = new Point(7,1);
        //then
        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testShouldBeFaildIfp2isnull(){
        //given
        Point p1 = new Point(3,4);
        Point p2 = new Point(7,1.2);;
        //when
        boolean result = p1.distance(p2) == 5.0;
        //then
        assertFalse(result);
    }

}
