package dev.donix.arlib.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;

public abstract class SWServiceNeed extends AREntity
{
    public SWServiceNeed(ARModel _model, String _path, String _shortName)
    {
        super(_model, _path, _shortName);
    }
}
