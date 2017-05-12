package musicplayer;

import org.apache.commons.io.FilenameUtils;

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

    private String strUrl;
    private URL url;

    public Server_Connector(String strUrl, URL url) {
        this.strUrl = strUrl;
        this.url = url;
    }

    public void connectToServer() {

        OutputStream outstream = null;
        InputStream is = null;

        try {
            URLConnection conn = new URL(strUrl).openConnection();
            is = conn.getInputStream();

            outstream=new FileOutputStream(new File("tmp/"+ FilenameUtils.getName(url.getPath().replaceAll("%20"," "))));
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
                is.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }
}