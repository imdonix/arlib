# AUTOSAR Parsing Library - arlib

A minimalist AUTOSAR parsing library for Java, focusing on performance and ease of use.
The library can parse `arxml` files and create a model populated with their contents as `AREntity`s and `ARProperty`s.

The library only supports RTE-relevant elements and is designed solely for parsing; the model cannot be modified.
For a list of implemented AR elements, see `src/entities`.

# How to use
Creating a model by parsing all `arxml` files in a directory:
``` java
import dev.donix.arlib.ARLib;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.entities.SWComponent;
import dev.donix.arlib.entities.SWPort;

ARModel model = ARLib.Load("./repository");

for (FileParseResult res : model.getFileParseResults())
{
    if(res.isSuccessful())
    {
        System.out.println(res.getPath() + " parsed successfully. [Found entities:" + res.getCollected().size() + "]");
    }
    else
    {
        System.out.println(res.getPath() + " failed to parse!");
        for (FileParseProblem problem : res.getProblems())
        {
            System.out.println(problem);
        }
    }
}
```

``` log
example.arxml parsed successfully. [Found entities: 19]
```

Accessing entities in the model and general usage:
``` java
SWComponent comp = model.get(SWComponent.class, "/Components/Sender");
List<SWPort> ports = comp.getPorts(SWPort.class);
for (SWPort p : ports)
{
    System.out.println("Port: " + p.getReference());
}
```

``` log
Port: /Components/Sender/SenderPort
Port: /Components/Sender/ClientPort
Port: /Components/Sender/ModePort
```