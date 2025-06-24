package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.props.InitValue;

public class SWParameterData extends AREntity
{
    private final InitValue _init;

    public SWParameterData(ARModel _model, String _path, String _shortName, InitValue init)
    {
        super(_model, _path, _shortName);
        this._init = init;
    }
}
