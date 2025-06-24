package arlib;

public final class ARUtils
{

    public static boolean isTrue(String autosarValue)
    {
        if(autosarValue == null)
        {
            return false;
        }

        return autosarValue.equalsIgnoreCase("true");
    }

}
