package arlib.parsers.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IARPropParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.ModeAccessPoint;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.MODE_ACCESS_POINT)
public class ModeAccessPointParser extends SimpleParser implements IARPropParser
{

    @Override
    public IARTagParser factory()
    {
        return new ModeAccessPointParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.combine(ARTag.MODE_GROUP_IREF, ARTag.R_MODE_GROUP_IN_ATOMIC_SWC_INSTANCE_REF, ARTag.CONTEXT_R_PORT_REF));
        addTagPathNeed(ARTag.combine(ARTag.MODE_GROUP_IREF, ARTag.R_MODE_GROUP_IN_ATOMIC_SWC_INSTANCE_REF, ARTag.TARGET_MODE_GROUP_REF));
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new ModeAccessPoint(
                model,
                getCaptured(ARTag.combine(ARTag.MODE_GROUP_IREF, ARTag.R_MODE_GROUP_IN_ATOMIC_SWC_INSTANCE_REF, ARTag.CONTEXT_R_PORT_REF)),
                getCaptured(ARTag.combine(ARTag.MODE_GROUP_IREF, ARTag.R_MODE_GROUP_IN_ATOMIC_SWC_INSTANCE_REF, ARTag.TARGET_MODE_GROUP_REF))
        );
    }

}
