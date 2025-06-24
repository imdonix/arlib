package dev.donix.arlib.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.props.InitValue;

public class SWParameterData extends AREntity
{
    private final InitValue _init;

    public SWParameterData(ARModel _model, String _path, String _shortName, InitValue init)
    {
        super(_model, _path, _shortName);
        this._init = init;
    }
}
