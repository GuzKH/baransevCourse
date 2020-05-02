package seventhTask;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

    @Test
    public void eqTests0(){
        Equation e = new Equation(1, 1, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public void eqTests1(){
        Equation e = new Equation(1, 2, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public void eqTests2(){
        Equation e = new Equation(1, 5, 6);
        Assert.assertEquals(e.rootNumber(), 2);
    }

    @Test
    public void eqTestsLinear(){
        Equation e = new Equation(0, 1, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public void eqTestsConstant(){
        Equation e = new Equation(0, 0, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public void eqTestsZero(){
        Equation e = new Equation(0, 0, 0);
        Assert.assertEquals(e.rootNumber(), -1);
    }
}
