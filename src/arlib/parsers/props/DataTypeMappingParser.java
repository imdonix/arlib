package arlib.parsers.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IARPropParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.DataTypeMappingRef;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.DATA_TYPE_MAPPING_REF)
public class DataTypeMappingParser extends SimpleParser implements IARPropParser
{
    @Override
    public IARTagParser factory()
    {
        return new DataTypeMappingParser();
    }

    @Override
    protected void declareNeeds() {}

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new DataTypeMappingRef(
                model,
                getCaptured(ARTag.ARLIB_SELF)
        );
    }


}
