package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARTag;
import arlib.entities.SWComposition;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.COMPOSITION_SW_COMPONENT_TYPE)
public class SWCompositionParser extends SimpleParser implements IAREntityParser
{

    private List<String> _prototypeComponentRefs;
    private List<String> _portRefs;
    private List<String> _connectorRefs;

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _prototypeComponentRefs = new ArrayList<>();
        _portRefs = new ArrayList<>();
        _connectorRefs = new ArrayList<>();
    }

    @Override
    public IARTagParser factory()
    {
        return new SWCompositionParser();
    }

    @Override
    protected void declareNeeds() {}

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWComposition(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                _prototypeComponentRefs,
                _portRefs,
                _connectorRefs
        );
    }

    @Override
    public void passEntity(AREntity entity) throws SAXException
    {
        if(parserPath().equals(ARTag.COMPONENT_LIST))
        {
            _prototypeComponentRefs.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.PORT_LIST))
        {
            _portRefs.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.CONNECTOR_LIST))
        {
            _connectorRefs.add(entity.getReference());
        }
        else
        {
            throw new SAXException("Illegal entity found {" + entity.getClass().getName() + "} during SWCompositionParser in '" + parserPath() + "'");
        }
    }
}
