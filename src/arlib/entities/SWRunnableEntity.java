package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARUtils;
import arlib.props.ModeAccessPoint;

import java.util.List;

public final class SWRunnableEntity extends AREntity
{

    private final String _minimumStartInterval;
    private final String _canBeInvokedConcurrently;
    private final String _symbol;

    private final List<String> _dataReadAccessRefs;
    private final List<String> _dataWriteAccessRefs;
    private final List<ModeAccessPoint> _modeAccessPoints;


    public SWRunnableEntity(ARModel _model, String _path, String _shortName, String minimumStartInterval, String canBeInvokedConcurrently, String symbol, List<String> dataReadAccessRefs, List<String> dataWriteAccessRefs, List<ModeAccessPoint> modeAccessPoints)
    {
        super(_model, _path, _shortName);
        this._minimumStartInterval = minimumStartInterval;
        this._canBeInvokedConcurrently = canBeInvokedConcurrently;
        this._symbol = symbol;
        this._dataReadAccessRefs = dataReadAccessRefs;
        this._dataWriteAccessRefs = dataWriteAccessRefs;
        this._modeAccessPoints = modeAccessPoints;
    }

    public String getSymbol()
    {
        return _symbol;
    }

    public String getMinimumStartInterval()
    {
        return _minimumStartInterval;
    }

    public boolean canBeInvokedConcurrently()
    {
        return ARUtils.isTrue(_canBeInvokedConcurrently);
    }

    /**
     * DATA-READ-ACCESSS & DATA-RECEIVE-POINT-BY-VALUES are not differentiated
     * Also SERVER-CALL-POINTS included
     */
    public List<SWDataAccess> getDataReads()
    {
        return _model.getAllByReference(SWDataAccess.class, _dataReadAccessRefs);
    }

    /**
     * DATA-WRITE-ACCESSS & DATA-SEND-POINTS are not differentiated
     */
    public List<SWDataAccess> getDataWrites()
    {
        return _model.getAllByReference(SWDataAccess.class, _dataWriteAccessRefs);
    }

    public List<ModeAccessPoint> getModeAccessPoints()
    {
        return _modeAccessPoints;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                " SYM=" + _symbol +
                " READ_C=" + _dataReadAccessRefs.size() +
                " WRITE_C=" + _dataWriteAccessRefs.size() +
                " MODE_C=" + _modeAccessPoints.size();
    }
}
