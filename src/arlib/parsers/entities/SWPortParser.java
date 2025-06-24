package arlib.parsers.entities;

import arlib.ARModel;
import arlib.ARTag;
import arlib.AREntity;
import arlib.entities.SWPRPort;
import arlib.entities.SWProviderPort;
import arlib.entities.SWReceiverPort;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.ARProp;
import arlib.props.ComSpec;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.P_PORT_PROTOTYPE)
@ARTagBinding(ARTag.R_PORT_PROTOTYPE)
@ARTagBinding(ARTag.PR_PORT_PROTOTYPE)
public class SWPortParser extends SimpleParser implements IAREntityParser
{

    private List<ComSpec> _specs;

    @Override
    public IARTagParser factory()
    {
        return new SWPortParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.PROVIDED_INTERFACE_TREF);
        addTagPathNeed(ARTag.REQUIRED_INTERFACE_TREF);
    }

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _specs = new ArrayList<>();
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        String type = getCaptured(ARTag.ARLIB_TAG);
        String path = getCaptured(ARTag.ARLIB_PATH);
        String name = getCaptured(ARTag.SHORT_NAME);
        String provIntRef = getCaptured(ARTag.PROVIDED_INTERFACE_TREF);
        String reqIntRef = getCaptured(ARTag.REQUIRED_INTERFACE_TREF);

        if(type.equals(ARTag.P_PORT_PROTOTYPE))
        {
            return new SWProviderPort(model, path, name, provIntRef);
        }
        else if(type.equals(ARTag.R_PORT_PROTOTYPE))
        {
            return new SWReceiverPort(model, path, name, reqIntRef);
        }
        else if(type.equals(ARTag.PR_PORT_PROTOTYPE))
        {
            return new SWPRPort(model, path, name, provIntRef);
        }
        else
        {
            throw new SAXException("Invalid tag for Port Parser -> " + type);
        }
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        if(parserPath().equals(ARTag.PROVIDED_COM_SPEC_LIST) || parserPath().equals(ARTag.REQUIRED_COM_SPECS))
        {
            _specs.add((ComSpec) prop);
        }
        else
        {
            throw new SAXException("ARProp is not applicable for SWPortParser -> '" + prop.getClass().getName() + "'");
        }
    }
}
