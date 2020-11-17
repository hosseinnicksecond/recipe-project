package home.train.Service;

import home.train.commands.MeasureCommand;

import java.util.Set;

public interface MeasureService {

    Set<MeasureCommand> findAll();
}
