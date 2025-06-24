package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;

import java.util.List;
import java.util.stream.Collectors;

public final class SWComponent extends AREntity
{

    private final String _longName;
    private final List<String> _portRefs;
    private final List<String> _ibRefs;

    public SWComponent(ARModel _model, String _path, String _shortName, String longName, List<String> portRefs, List<String> ibRefs)
    {
        super(_model, _path, _shortName);
        _longName = longName;
        _portRefs = portRefs;
        _ibRefs = ibRefs;
    }

    public String getLongName()
    {
        return _longName;
    }

    @SuppressWarnings("unchecked")
    public <T extends SWPort> List<T> getPorts(Class<T> clazz)
    {
        return (List<T>) _model.getAllByReference(SWPort.class, _portRefs).stream().filter(clazz::isInstance).collect(Collectors.toList());
    }

    public List<SWInternalBehaviour> getInternalBehaviour()
    {
        return _model.getAllByReference(SWInternalBehaviour.class, _ibRefs);
    }

    @Override
    public String toString()
    {
        return super.toString() + " LONG_NAME= " + _longName + " PORT_COUNT=" + _portRefs.size() + " IB_COUNT=" + _ibRefs.size();
    }
}
