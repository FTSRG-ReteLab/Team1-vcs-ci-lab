package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainController controller;
    TrainUser user;
    TrainSensor sensor;

    @Before
    public void before() {
        controller = Mockito.mock(TrainController.class);
        user = Mockito.mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void Sensor_WhenSpeedLimitLessThanTwiceOfRefSpeed_Alarm() {
        when(controller.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(24);
        verify(user, times(1)).setAlarmState(true);

    }

    @Test
    public void Sensor_WhenSpeedLimitLessThanLowerLimit_Alarm() {
        sensor.overrideSpeedLimit(-2);
        verify(user, times(1)).setAlarmState(true);

    }

    @Test
    public void Sensor_WhenSpeedLimitMoreThanUpperLimit_Alarm() {
        sensor.overrideSpeedLimit(7000);
        verify(user, times(1)).setAlarmState(true);

    }

    @Test
    public void Sensor_WhenSpeedLimitIsJustRigth_NoAlarm() {
        when(controller.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(40);
        verify(user, times(0)).setAlarmState(true);

    }
}
