package arlib.parsers.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IARPropParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.CSComSpec;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.CLIENT_COM_SPEC)
@ARTagBinding(ARTag.SERVER_COM_SPEC)
public class ComSpecParser extends SimpleParser implements IARPropParser
{

    @Override
    public IARTagParser factory()
    {
        return new ComSpecParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.OPERATION_REF);
        addTagPathNeed(ARTag.QUEUE_LENGTH);
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new CSComSpec(
            model,
            getCaptured(ARTag.OPERATION_REF),
            getCaptured(ARTag.QUEUE_LENGTH)
        );
    }
}
