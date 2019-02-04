package hu.bme.mit.train.sensor;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import java.util.Date;

public class TrainTachograph {
    private Table<Date,Integer, Integer> data= HashBasedTable.create();

    public TrainTachograph(TrainController controller, TrainUser user) {
        this.controller = controller;
        this.user = user;
    }

    private TrainController controller;
    private TrainUser user;

    public void log(){
        data.put(new Date(),controller.getReferenceSpeed(),user.getJoystickPosition());
    }

    public Table<Date,Integer, Integer> getTable(){
        return data;
    }
}
