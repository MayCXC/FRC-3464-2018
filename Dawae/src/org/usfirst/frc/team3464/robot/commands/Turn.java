package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;

public class Turn extends Move {
    public Turn(double drive, DoubleSupplier direction, DoublePredicate finished) {
    	super(drive, -drive, direction, finished);
    }
}
