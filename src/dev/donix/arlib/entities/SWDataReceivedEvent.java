package dev.donix.arlib.entities;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.props.ModeIRef;

import java.util.List;

//TODO
public class SWDataReceivedEvent extends SWEvent
{

    public SWDataReceivedEvent(ARModel _model, String _path, String _shortName, String startOnEventRef, List<ModeIRef> modeIRefs, List<ModeIRef> disabledModeIRefs)
    {
        super(_model, _path, _shortName, startOnEventRef, modeIRefs, disabledModeIRefs);
    }
}
