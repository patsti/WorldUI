package patrik.worldui.utils;

import java.io.*;
import java.net.URL;
import java.util.Base64;

public class Utils {

    /** Read the object from Base64 string. Used when fetching object from Rest-API */
    public static Object fromString( String s ) {
        try {
            byte [] data = Base64.getDecoder().decode( s );
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(  data ) );
            Object o  = ois.readObject();
            ois.close();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Serializer, special fix to be passable in URL
    public static String serialize(Serializable o) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( o );
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray()).replace("/g", "QQQQQ").replace("+", "YYYYY");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Deserializer, special fix to reverse the extra serializer func.
    public static Object deserialize(String s) {
        s = s.replace("QQQQQ", "/g").replace("YYYYY", "+");
        try {
            byte [] data = Base64.getDecoder().decode( s );
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(  data ) );
            Object o  = ois.readObject();
            ois.close();
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String webParser(String urlString) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        String full = "";

        try {
            String webSite = "";


            url = new URL(urlString);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
            int insideIngredient = 0;
            boolean insideInstruction = false;
            int insideLI = 0;

            while ((line = br.readLine()) != null) {
                full+=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return full;
    }
}

