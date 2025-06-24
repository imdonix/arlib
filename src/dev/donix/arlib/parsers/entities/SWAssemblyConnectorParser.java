package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.SWAssemblyConnector;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
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
