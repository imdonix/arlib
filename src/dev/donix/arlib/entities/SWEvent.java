package dev.donix.arlib.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.exceptions.EntityNotExistException;
import dev.donix.arlib.props.ModeIRef;

import java.util.List;

public abstract class SWEvent extends AREntity
{
    protected final String _startOnEventRef;
    protected final List<ModeIRef> _modeIRef;
    protected final List<ModeIRef> _disabledModeIRefs;

    public SWEvent(ARModel _model, String _path, String _shortName, String startOnEventRef, List<ModeIRef> modeIRefs, List<ModeIRef> disabledModeIRefs)
    {
        super(_model, _path, _shortName);
        _modeIRef = modeIRefs;
        _disabledModeIRefs = disabledModeIRefs;
        _startOnEventRef = startOnEventRef;
    }

    public SWRunnableEntity getStartOnEvent() throws EntityNotExistException
    {
        return _model.get(SWRunnableEntity.class, _startOnEventRef);
    }

    public List<ModeIRef> getModeIRef()
    {
        return _modeIRef;
    }

    public List<ModeIRef> getDisabledModeIRefs()
    {
        return _disabledModeIRefs;
    }
}
