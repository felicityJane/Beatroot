package musicplayer;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Federica on 29/04/2017.
 */
public class Server_Connector extends Service<String> {

    private String strUrl;
    private URL url;
    private File file;

    public Server_Connector(String strUrl, URL url) {
        this.strUrl = strUrl;
        this.url = url;
    }
    public Server_Connector(String strUrl, URL url, File file) {
        this.strUrl = strUrl;
        this.url = url;
        this.file = file;
    }

    @Override
    public Task<String> createTask() {

        return new Task<String>() {
            @Override
            public String call() throws Exception {
                OutputStream outstream = null;
                InputStream is = null;
                int len = 0;

                try {
                    URLConnection conn = new URL(strUrl).openConnection();
                    is = conn.getInputStream();

                    if (file == null) {
                        outstream = new FileOutputStream(new File("tmp/" + FilenameUtils.getName(url.getPath().replaceAll("%20", " "))));
                    } else {
                        outstream = new FileOutputStream(file);
                    }
                    byte[] buffer = new byte[4096];

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
                String lenS = Integer.toString(len);
                return lenS;
            }
        };

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
