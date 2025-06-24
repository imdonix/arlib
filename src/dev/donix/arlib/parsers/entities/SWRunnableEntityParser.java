package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.SWRunnableEntity;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.props.ModeAccessPoint;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.RUNNABLE_ENTITY)
public class SWRunnableEntityParser extends SimpleParser implements IAREntityParser
{
    private List<String> _dataReadAccessRefs;
    private List<String> _dataWriteAccessRefs;
    private List<ModeAccessPoint> _modeAccessPoints;

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _dataReadAccessRefs = new ArrayList<>();
        _dataWriteAccessRefs = new ArrayList<>();
        _modeAccessPoints = new ArrayList<>();
    }

    @Override
    public IARTagParser factory()
    {
        return new SWRunnableEntityParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.MINIMUM_START_INTERVAL);
        addTagPathNeed(ARTag.CAN_BE_INVOKED_CONCURRENTLY);
        addTagPathNeed(ARTag.SYMBOL);
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWRunnableEntity(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.MINIMUM_START_INTERVAL),
                getCaptured(ARTag.CAN_BE_INVOKED_CONCURRENTLY),
                getCaptured(ARTag.SYMBOL),
                _dataReadAccessRefs,
                _dataWriteAccessRefs,
                _modeAccessPoints
        );
    }

    @Override
    public void passEntity(AREntity entity) throws SAXException
    {
        if (parserPath().equals(ARTag.DATA_READ_ACCESS_LIST) || parserPath().equals(ARTag.DATA_RECEIVE_POINT_BY_VALUE_LIST) || parserPath().equals(ARTag.DATA_RECEIVE_POINT_BY_ARGUMENTS))
        {
            _dataReadAccessRefs.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.DATA_WRITE_ACCESS_LIST) || parserPath().equals(ARTag.DATA_SEND_POINT_LIST))
        {
            _dataWriteAccessRefs.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.SERVER_CALL_POINT_LIST))
        {
            _dataReadAccessRefs.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.READ_LOCAL_VARIABLE_LIST) || parserPath().equals(ARTag.WRITTEN_LOCAL_VARIABLES))
        {
            //ignore
        }
        else
        {
            throw new SAXException("Unknown entity found inside SWRunnableEntity -> " + entity.getClass().getName() + " PATH=(" + parserPath() + ")");
        }
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        if (parserPath().equals(ARTag.MODE_ACCESS_POINT_LIST))
        {
            _modeAccessPoints.add((ModeAccessPoint) prop);
        }
        else
        {
            throw new SAXException("Unknown prop found inside SWRunnableEntity -> " + prop.getClass().getName());
        }
    }
}
