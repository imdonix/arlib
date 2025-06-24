package dev.donix.arlib.parsers.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IARPropParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.props.RoleBasedPortAssignment;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.ROLE_BASED_PORT_ASSIGNMENT)
public class RoleBasedPortAssignmentParser extends SimpleParser implements IARPropParser
{

    @Override
    public IARTagParser factory()
    {
        return new RoleBasedPortAssignmentParser();
    }


    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.PORT_PROTOTYPE_REF);
        addTagPathNeed(ARTag.ROLE);
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new RoleBasedPortAssignment(
                model,
                getCaptured(ARTag.PORT_PROTOTYPE_REF),
                getCaptured(ARTag.ROLE)
        );
    }



}
