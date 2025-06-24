package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.entities.SWParameterData;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.InitValue;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.PARAMETER_DATA_PROTOTYPE)
public class SWParameterDataParser extends SimpleParser implements IAREntityParser
{

    private InitValue _init;

    @Override
    public IARTagParser factory()
    {
        return new SWParameterDataParser();
    }

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _init = null;
    }

    @Override
    protected void declareNeeds()
    {
        //TODO
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWParameterData(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                _init
        );
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        if(parserPath().equals(ARTag.INIT_VALUE))
        {
            if(_init == null)
            {
                _init = (InitValue) prop;
            }
            else
            {
                throw new SAXException("Multiple init value found for SWParameterDataParser");
            }
        }
        else
        {
            throw new SAXException("Illegal prop {" + prop.getClass().getName() + "} was found for SWParameterDataParser");
        }
    }
}
