package dev.donix.arlib.tests;

import dev.donix.arlib.ARLib;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.entities.*;
import dev.donix.arlib.exceptions.EntityNotExistException;
import dev.donix.arlib.parsers.FileParseResult;
import dev.donix.arlib.props.ModeAccessPoint;
import dev.donix.arlib.props.PortApiOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ARLibTests
{

    private static ARModel model;

    @BeforeAll
    static void ParseResourcesFolder()
    {
        assertDoesNotThrow(() -> {
            model = ARLib.Load("resources");

            for (FileParseResult res : model.getFileParseResults())
            {
                if(res.isSuccessful())
                {
                    if(res.getPath().toString().equals("resources\\example.arxml"))
                    {
                        assertEquals(res.getCollected().size(), 19);
                    }
                }
                else
                {
                    assertTrue(false, "Failed to parse <" + res.getPath() + ">");
                }
            }
        });
    }

    @Test
    void Interfaces()
    {
        List<Interface> interfaces = model.getAll(Interface.class);
        assertEquals(2, interfaces.size());
    }

    @Test
    void Compositions()
    {
        assertDoesNotThrow(() ->
        {

            SWComposition comp = model.get(SWComposition.class, "/Components/MyComposition");
            assertEquals("MyComposition", comp.getShortName());

            List<SWPrototypeComponent> components = comp.getPrototypeComponents();
            assertEquals(2, components.size());
            assertEquals("Sender", components.get(0).getComponent().getShortName());

            List<SWPort> ports = comp.getPorts();
            assertEquals(1, ports.size());

            List<SWConnector> connectors = comp.getConnectors();
            assertEquals(1, connectors.size());
        });
    }

    @Test
    void Components()
    {
        assertDoesNotThrow(() -> {

            SWComponent component = model.get(SWComponent.class, "/Components/Sender");
            assertEquals("Sender Component", component.getLongName());

            Set<String> providerNames = new HashSet<>(Arrays.asList(
                    "SenderPort"
                    ));

            Set<String> receiverports = new HashSet<>(Arrays.asList(
                    "ClientPort",
                    "ModePort"
            ));

            for (SWPort pport : component.getPorts(SWProviderPort.class))
            {
                boolean found = providerNames.contains(pport.getShortName());
                assertTrue(found);
            }

            for (SWPort rport : component.getPorts(SWReceiverPort.class))
            {
                boolean found = receiverports.contains(rport.getShortName());
                assertTrue(found);
            }

            List<SWInternalBehaviour> ibs = component.getInternalBehaviour();
            assertEquals(ibs.size(), 1);

            SWInternalBehaviour ib = ibs.get(0);
            assertFalse(ib.isSupportMultipleInstantiation());

            List<SWEvent> events = ib.getEvents(SWEvent.class);
            assertEquals(events.size(), 1);

            SWTimingEvent event1 = (SWTimingEvent) events.get(0);
            assertEquals("TimingEvent1", event1.getShortName());
            assertEquals("0.1", event1.getPeriod());


            List<PortApiOption> options = ib.getPortApiOptions();
            assertEquals(1,options.size());
            assertFalse(options.get(0).isEnableTakeAddress());
            assertFalse(options.get(0).isIndirectApi());
            assertEquals("SenderPort", options.get(0).getPortRef().getShortName());

            List<SWRunnableEntity> res = ib.getRunnableEntities();
            assertEquals(1, res.size());

            SWRunnableEntity re = res.get(0);
            assertEquals("SendRunnable", re.getShortName());
            assertEquals("timing", re.getSymbol());
            assertFalse(re.canBeInvokedConcurrently());
            assertEquals("0", re.getMinimumStartInterval());

            List<SWDataAccess> reads = re.getDataReads();
            List<SWDataAccess> writes = re.getDataWrites();

            assertEquals(1, reads.size());
            assertEquals(1, writes.size());

            SWDataAccess r = reads.get(0);
            assertEquals("CallPoint1", r.getShortName());
            assertEquals("ClientPort", r.getPortRef().getShortName());
            assertThrowsExactly(EntityNotExistException.class, r::getTargetDataRef);

            SWDataAccess w = writes.get(0);
            assertEquals("SendPoint", w.getShortName());
            assertEquals("SenderPort", w.getPortRef().getShortName());

            List<ModeAccessPoint> maps = re.getModeAccessPoints();
            assertEquals(0, maps.size());

            List<SWServiceDependency> deps = ib.getServiceDependencies();
            assertEquals(0, deps.size());

        });

    }
}