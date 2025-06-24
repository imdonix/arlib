package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARTag;
import arlib.entities.SWAssemblyConnector;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.ASSEMBLY_SW_CONNECTOR)
public class SWAssemblyConnectorParser extends SimpleParser implements IAREntityParser
{
    @Override
    public IARTagParser factory()
    {
        return new SWAssemblyConnectorParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.combine(ARTag.PROVIDER_IREF, ARTag.CONTEXT_COMPONENT_REF));
        addTagPathNeed(ARTag.combine(ARTag.PROVIDER_IREF, ARTag.TARGET_P_PORT_REF));
        addTagPathNeed(ARTag.combine(ARTag.REQUESTER_IREF, ARTag.CONTEXT_COMPONENT_REF));
        addTagPathNeed(ARTag.combine(ARTag.REQUESTER_IREF, ARTag.TARGET_R_PORT_REF));
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWAssemblyConnector(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.combine(ARTag.PROVIDER_IREF, ARTag.CONTEXT_COMPONENT_REF)),
                getCaptured(ARTag.combine(ARTag.PROVIDER_IREF, ARTag.TARGET_P_PORT_REF)),
                getCaptured(ARTag.combine(ARTag.REQUESTER_IREF, ARTag.CONTEXT_COMPONENT_REF)),
                getCaptured(ARTag.combine(ARTag.REQUESTER_IREF, ARTag.TARGET_R_PORT_REF))
        );
    }

}
