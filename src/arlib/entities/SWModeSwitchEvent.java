package arlib.entities;

import arlib.ARModel;
import arlib.props.ModeIRef;

import java.util.List;

public final class SWModeSwitchEvent extends SWEvent
{
    private final String _activation;

    public SWModeSwitchEvent(ARModel _model, String _path, String _shortName, String startOnEventRef, String activation, List<ModeIRef> modeIRef, List<ModeIRef> disabledModeIRef)
    {
        super(_model, _path, _shortName, startOnEventRef, modeIRef, disabledModeIRef);
        this._activation = activation;
    }

    public String getActivation()
    {
        return _activation;
    }
}
