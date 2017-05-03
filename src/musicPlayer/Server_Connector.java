package musicplayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Federica on 29/04/2017.
 */
public class Server_Connector {

    public void connectToServer() {

        OutputStream outstream = null;

        try {
            URLConnection conn = new URL("http://www.webshare.hkr.se/FECO0002/alice%20in%20chains%20-%2001%20-%20them%20bones.mp3").openConnection();
            InputStream is = conn.getInputStream();

            outstream = new FileOutputStream(new File("tmp/alice in chains - 01 - them bones.mp3"));
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                outstream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }
}
