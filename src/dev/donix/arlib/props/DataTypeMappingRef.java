package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;

public class DataTypeMappingRef extends ARProp
{

    private final String _dataTypeMappingRef;

    public DataTypeMappingRef(ARModel model, String dataTypeMappingRef)
    {
        super(model);
        this._dataTypeMappingRef = dataTypeMappingRef;
    }

    @Override
    public String toString()
    {
        return _dataTypeMappingRef;
    }
}
