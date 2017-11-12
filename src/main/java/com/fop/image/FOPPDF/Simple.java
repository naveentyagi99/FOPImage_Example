package com.fop.image.FOPPDF;

import org.apache.fop.apps.*;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

@WebServlet("/simple")
public class Simple extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        convertToPDF(response);


    }

    public void convertToPDF(HttpServletResponse response)   throws  IOException{
        //OutputStream out = null;
        ByteArrayOutputStream out = null;
        TransformerFactory tFactory = TransformerFactory.newInstance();
        try {
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

            //Setup a buffer to obtain the content length
             out = new ByteArrayOutputStream();

            //Setup FOP
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PNG, out);

            //Setup Transformer
            Source xsltSrc = new StreamSource(new File("D:\\Study\\Dev\\IntelliJ\\nodejs\\FOP Image\\src\\main\\webapp\\template.xsl"));
            Transformer transformer = tFactory.newTransformer(xsltSrc);

            //Make sure the XSL transformation's result is piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            //Setup input
            Source src = new StreamSource(new File("D:\\Study\\Dev\\IntelliJ\\nodejs\\FOP Image\\src\\main\\webapp\\Employees.xml"));

            //Start the transformation and rendering process
            transformer.transform(src, res);

            //Prepare response
            response.setContentType(MimeConstants.MIME_PNG);
            response.setContentLength(out.size());

            //Send content to Browser
            response.getOutputStream().write(out.toByteArray());
            response.getOutputStream().flush();
        }
        catch(Exception e){

        }
        finally {
            out.close();
        }
    }
}