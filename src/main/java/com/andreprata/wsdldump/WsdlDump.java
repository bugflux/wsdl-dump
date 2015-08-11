package com.andreprata.wsdldump;

import jlibs.xml.sax.crawl.CrawlerListener;
import jlibs.xml.sax.crawl.XMLCrawler;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by ndray on 11/08/15.
 */
public class WsdlDump {
    public static void main(String args[]) throws IOException, URISyntaxException {
        File baseFile = new File(stripUri(args[0]));
        baseFile.getParentFile().mkdirs();
        baseFile.createNewFile();

        new XMLCrawler().crawl(new InputSource(args[0]), preserveStructureCrawlerListener, baseFile);
    }

    private static final CrawlerListener preserveStructureCrawlerListener = new CrawlerListener() {
        @Override
        public boolean doCrawl(URL url) {
            return true;
        }

        @Override
        public File toFile(URL url, String s) {
            try {
                File file = new File(stripUri(String.valueOf(url)));
                file.getParentFile().mkdirs();
                file.createNewFile();

                return file;
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    };

    private static final String stripUri(String s) throws URISyntaxException {
        return new URI(s).getPath().substring(1); // remove leading slash
    }
}
