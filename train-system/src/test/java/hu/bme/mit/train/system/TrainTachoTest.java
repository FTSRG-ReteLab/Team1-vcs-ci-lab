package hu.bme.mit.train.system;

import hu.bme.mit.train.sensor.TrainTachograph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrainTachoTest {

    TrainTachograph tacho;
    @Before
    public void before() {
        TrainSystem system = new TrainSystem();
        tacho = system.getTacho();
    }

    @Test
    public void TestTacho() {
        tacho.log();
        Assert.assertTrue(!tacho.getTable().isEmpty());
    }
}
