package com.livepass.xpd.xmlparserdemo.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.livepass.xpd.xmlparserdemo.R;
import com.livepass.xpd.xmlparserdemo.handlers.ErrorCode;
import com.livepass.xpd.xmlparserdemo.handlers.LoginHandler;
import com.livepass.xpd.xmlparserdemo.models.User;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvUser;
    TextView tvXml;

    //for demo
    //it should be called from web service
    String xmlString = "<?xml version=\"1.0\"?>\n" +
            "<SOAP-ENV:Envelope xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" \n" +
            "  xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "    <SOAP-ENV:Body>\n" +
            "        <User>\n" +
            "            <id>9</id>\n" +
            "            <user_name>vuvt</user_name>\n" +
            "            <email>thaivu101@gmail.com</email>\n" +
            "        </User>\n" +
            "    </SOAP-ENV:Body>\n" +
            "</SOAP-ENV:Envelope>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init component
        tvUser = (TextView) findViewById(R.id.tv_content);
        tvXml = (TextView) findViewById(R.id.tv_xml);

        //set data for component
        tvXml.setText(xmlString);
    }

    public void doXmlParser(View view) {
        User user = (User) xmlParser(xmlString);
        if (user != null) {
            String str = "User id: " + user.getId() +
                    "\nUser name: " + user.getUserName() +
                    "\nEmail: " + user.getEmail();
            tvUser.setText(str);
        } else {
            tvUser.setText("Error occurred!");
        }
    }

    /**
     * Parser XML
     *
     * @param strXml xml string get from WS (WebService) for parser
     * @return Object
     */
    private Object xmlParser(String strXml) {
        if (strXml == null || strXml.length() == 0) {
            ErrorCode err = new ErrorCode();
            err.setErrorID("errorID");
            err.setErrorMsg("Can't connect to server!");
            return err;
        }
        byte xmlBytes[] = strXml.getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlBytes);
        SAXParserFactory saxPF = SAXParserFactory.newInstance();
        SAXParser saxParser;
        try {
            saxParser = saxPF.newSAXParser();
            XMLReader xr = saxParser.getXMLReader();
            LoginHandler handler = new LoginHandler();
            xr.setContentHandler(handler);
            xr.parse(new InputSource(byteArrayInputStream));
            return handler.getResult();

        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
