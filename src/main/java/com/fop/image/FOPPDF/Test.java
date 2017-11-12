package com.fop.image.FOPPDF;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.OutputStream;

public class Test {

    public static void main(String arr[]){
        convertToPDF();
    }

    public static void convertToPDF()   {

        try {
            File xsltFile = new File("C:\\Users\\Naveen Tyagi\\Desktop\\template.xsl");
            StreamSource xmlSource = new StreamSource(new File("C:\\Users\\Naveen Tyagi\\Desktop\\Employees.xml"));
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            OutputStream out;
            out = new java.io.FileOutputStream("C:\\Users\\Naveen Tyagi\\Desktop\\test.pdf");

            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            Result res = new SAXResult(fop.getDefaultHandler());

            transformer.transform(xmlSource, res);
        }
        catch(Exception e){

        }
        finally {

        }
    }
}
