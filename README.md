# AUTOSAR Parsing Library - arlib

A minimalist AUTOSAR parsing library for Java focusing on performance and ease of use.
The library can parse `arxml` files and create a model populated with is contents as `AREnity`s and `ARProp`.

The library only support RTE relevant elements, and can be only used for parsing, the model cannot be modified.
For implemented AR elements check `src/entities`.

# How to use
Creating a model by parsing all `arxml` files in a directory:
``` java
import arlib.ARLib;
import arlib.ARModel;
import arlib.entities.SWComponent;
import arlib.entities.SWPort;

ARModel model = ARLib.Load("./repository");

for (FileParseResult res : model.getFileParseResults())
{
    if(res.isSuccessful())
    {
        System.out.println(res.getPath() + " parsed successful. [Found entities:" + res.getCollected().size() + "]");
    }
    else
    {
        System.out.println(res.getPath() + " parsed failed!");
        for (FileParseProblem problem : res.getProblems())
        {
            System.out.println(problem);
        }
    }
}
```

``` log
example.arxml parsed successful. [Found entities: 19]
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