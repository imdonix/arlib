package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;

import java.util.List;

public final class SWComposition extends AREntity
{
    private final List<String> _prototypeComponentRefs;
    private final List<String> _portRefs;
    private final List<String> _connectorRefs;

    public SWComposition(ARModel _model, String _path, String _shortName, List<String> _prototypeComponentRefs, List<String> _portRefs, List<String> _connectorRefs)
    {
        super(_model, _path, _shortName);
        this._prototypeComponentRefs = _prototypeComponentRefs;
        this._portRefs = _portRefs;
        this._connectorRefs = _connectorRefs;
    }

    public List<SWPrototypeComponent> getPrototypeComponents()
    {
        return _model.getAllByReference(SWPrototypeComponent.class, _prototypeComponentRefs);
    }

    public List<SWPort> getPorts()
    {
        return _model.getAllByReference(SWPort.class, _portRefs);
    }

    public List<SWConnector> getConnectors()
    {
        return _model.getAllByReference(SWConnector.class, _connectorRefs);
    }

}
