package com.androidmagazine.shapes;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.util.Log;

public class XMLParser {
	private String xml = null;

	// constructor
	public XMLParser() {
		
	}
	
	/**
	 * Getting XML from SD card
	 * @param filePath string
	 * */
	public String getXmlFromFile(String filePath) {
		
		BufferedReader br; 

		try {
			// defaultHttpClient
			File file = new File(filePath);
		
			StringBuilder text = new StringBuilder();
		
		    br= new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) {
		        text.append(line);
		        text.append('\n');
		}
		    
		xml=text.toString();
		br.close();
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			
		}
		// return XML
		return xml;
	}
	
	
	/**
	 * Getting XML from URL making HTTP request
	 * @param url string
	 * */
	public String getXmlFromUrl(String url) {
	
			
			try {
				ReadXmlFromWebAsynTask asynTask = new ReadXmlFromWebAsynTask();
				asynTask.execute(url);
				xml=asynTask.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return xml;
	}
	
	/**
	 * Getting XML DOM element
	 * @param XML string
	 * */
	public Document getDomElement(String xml){
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
		
			InputStream input = new ByteArrayInputStream(xml.getBytes());

			
			UnicodeBOMInputStream inputStreamReader= new UnicodeBOMInputStream(input).skipBOM();
		        is.setByteStream(inputStreamReader);


		        doc = db.parse(is); 

			} catch (ParserConfigurationException e) {
				Log.e("Error: ", e.getMessage());
				return null;
			} catch (SAXException e) {
				Log.e("Error: ", e.getMessage());
	            return null;
			} catch (IOException e) {
				Log.e("Error: ", e.getMessage());
				return null;
			}


		return doc;
	}
	
	/** Getting node value
	  * @param elem element
	  */
	 public final String getElementValue( Node elem ) {
	 
		 Node child;
	     if( elem != null){
	         if (elem.hasChildNodes()){
	             for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
	                 if( child.getNodeType() == Node.TEXT_NODE  ){
	                     return child.getNodeValue();
	                 }
	             }
	         }
	     }
	     return "";
	     
	 }
	 
	 /**
	  * Getting node value
	  * @param Element node
	  * @param key string
	  * */
	 public String getValue(Element item, String str) {		
			NodeList n = item.getElementsByTagName(str);		
			return this.getElementValue(n.item(0));
		}

	 public void save(Document doc,String filePath)
	 {
		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
		 Transformer transformer;
			try {
				
				transformer = transformerFactory.newTransformer();
				
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filePath));
				
				Properties outFormat = createProperty();
	            transformer.setOutputProperties( outFormat );
	            
	            transformer.transform(source, result);
				
			}
			catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	 }

	private Properties createProperty() {
		Properties outFormat = new Properties();
		outFormat.setProperty( OutputKeys.INDENT, "yes" );
		outFormat.setProperty( OutputKeys.METHOD, "xml" );
		outFormat.setProperty( OutputKeys.OMIT_XML_DECLARATION, "no" );
		outFormat.setProperty( OutputKeys.VERSION, "1.0" );
		outFormat.setProperty( OutputKeys.ENCODING, "UTF-8" );
		return outFormat;
	}

	private class ReadXmlFromWebAsynTask extends AsyncTask<String,Void,String>
	{

	

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			HttpParams httpParameters = new BasicHttpParams();
			// Set the timeout in milliseconds until a connection is established.
			int timeoutConnection = 9000;
			HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
			// Set the default socket timeout (SO_TIMEOUT) 
			// in milliseconds which is the timeout for waiting for data.
			int timeoutSocket = 9000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			
			HttpGet httpPost = new HttpGet(params[0]);
			String result=null;
			
			HttpResponse httpResponse;
			try {
				httpResponse = httpClient.execute(httpPost);
				
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return result;
			
		}
		
	}
	
}
