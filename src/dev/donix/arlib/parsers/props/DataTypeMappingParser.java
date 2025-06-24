package dev.donix.arlib.parsers.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IARPropParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.props.DataTypeMappingRef;
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
