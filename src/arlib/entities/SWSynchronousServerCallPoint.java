package arlib.entities;

import arlib.ARModel;

public final class SWSynchronousServerCallPoint extends SWDataAccess
{
    public SWSynchronousServerCallPoint(ARModel _model, String _path, String _shortName, String _portPrototypeRef, String _targetDataRef)
    {
        super(_model, _path, _shortName, _portPrototypeRef, _targetDataRef);
    }
}
