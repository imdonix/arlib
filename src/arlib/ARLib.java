package arlib;

import arlib.entities.InterfaceElement;
import arlib.entities.SWDataAccess;
import arlib.entities.SWVariableAccess;
import arlib.exceptions.EntityNotExistException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ARLib
{

    public static ARModel Load(String... rootPaths) throws IOException
    {
        List<Path> arxmls = new ArrayList<>();

        for (String root : rootPaths)
        {
            Path folderPath = Paths.get(root);
            File folderFile = folderPath.toAbsolutePath().toFile();
            if(folderFile.exists() && folderFile.isDirectory())
            {
                try (Stream<Path> walk = Files.walk(folderPath))
                {
                    List<Path> result = walk.filter(Files::isRegularFile).collect(Collectors.toList());
                    for (Path file : result)
                    {
                        if(file.toString().endsWith(".arxml"))
                        {
                            arxmls.add(file);
                        }
                    }
                }
            }
            else
            {
                throw new IOException("Given path is invalid: " + root);
            }
        }

        return new ARModel(arxmls);
    }


    public static HashMap<InterfaceElement, HashSet<SWDataAccess>> MapInterfaceElements(ARModel model)
    {
        HashMap<InterfaceElement, HashSet<SWDataAccess>> map = new HashMap<>();
        List<SWDataAccess> accesses = model.getAll(SWDataAccess.class);

        for (SWDataAccess access : accesses)
        {
            try
            {
                InterfaceElement element = access.getTargetDataRef();

                if(element != null) //
                {
                    HashSet<SWDataAccess> bucket = map.get(element);
                    if (bucket == null)
                    {
                        HashSet<SWDataAccess> tmpBucket = new HashSet<>();
                        tmpBucket.add(access);
                        map.put(element, tmpBucket);
                    }
                    else
                    {
                        bucket.add(access);
                    }
                }
                else
                {
                    System.out.println("Interface Element reference does not exist for does not exist for " + access.getReference());
                }
            }
            catch (EntityNotExistException exception)
            {
                System.out.println("Interface Element does not exist for " + access.getReference());
            }
        }

        return map;
    }
}
