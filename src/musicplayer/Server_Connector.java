package musicplayer;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Federica on 29/04/2017.
 */
public class Server_Connector {

    private String strUrl;
    private URL url;
    private int[] progress = new int[10];

    public Server_Connector(String strUrl, URL url) {
        this.strUrl = strUrl;
        this.url = url;
    }

    public int[] getProgress() {
        return progress;
    }

    public void connectToServer() {

        OutputStream outstream = null;
        InputStream is = null;

        try {
            URLConnection conn = new URL(strUrl).openConnection();
            is = conn.getInputStream();


            outstream = new FileOutputStream(new File("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " "))));
            byte[] buffer = new byte[4096];
            int len;
            int counter = 0;
            while ((len = is.read(buffer)) > 0) {
                outstream.write(buffer, 0, len);
                if (counter < 9) {
                    progress[counter] = getFileSize(url) / len;
                    counter++;
                }
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

    public int getFileSize(URL url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            return -1;
        } finally {
            conn.disconnect();
        }
    }
}
