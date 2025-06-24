package arlib.parsers.entities;

import arlib.ARModel;
import arlib.ARTag;
import arlib.AREntity;
import arlib.entities.SWComponent;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.APPLICATION_SW_COMPONENT_TYPE)
@ARTagBinding(ARTag.COMPLEX_DEVICE_DRIVER_SW_COMPONENT_TYPE)
@ARTagBinding(ARTag.SENSOR_ACTUATOR_SW_COMPONENT_TYPE)
public class SWComponentParser extends SimpleParser implements IAREntityParser
{

    private List<String> _portRefs;
    private List<String> _ibRefs;

    @Override
    public IARTagParser factory()
    {
        return new SWComponentParser();
    }

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _portRefs = new ArrayList<>();
        _ibRefs = new ArrayList<>(1);
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.combine(ARTag.LONG_NAME, ARTag.L4));
    }

    @Override
    public AREntity end(ARModel model)
    {
        return new SWComponent(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.combine(ARTag.LONG_NAME, ARTag.L4)),
                _portRefs,
                _ibRefs);
    }

    @Override
    public void passEntity(AREntity entity) throws SAXException
    {
        if(parserPath().equals(ARTag.PORT_LIST))
        {
            _portRefs.add(entity.getReference());
        }

        if(parserPath().equals(ARTag.INTERNAL_BEHAVIOR_LIST))
        {
            _ibRefs.add(entity.getReference());
        }
    }
}
