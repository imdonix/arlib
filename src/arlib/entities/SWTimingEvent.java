package arlib.entities;

import arlib.ARModel;
import arlib.props.ModeIRef;

import java.util.List;

public final class SWTimingEvent extends SWEvent
{

    private final String _period;

    public SWTimingEvent(ARModel _model, String _path, String _shortName, String startOnEventRef, String period, List<ModeIRef> modeIRefs, List<ModeIRef> disabledModeIRefs)
    {
        super(_model, _path, _shortName, startOnEventRef, modeIRefs, disabledModeIRefs);
        this._period = period;
    }

    public String getPeriod()
    {
        return _period;
    }
}
