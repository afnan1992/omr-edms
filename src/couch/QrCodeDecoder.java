package couch;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.omr.exceptions.QrFailedToDetect;

public class QrCodeDecoder {

	private HttpURLConnection connection;
	public String QrCode;
	public String getQrCode() {
		return QrCode;
	}
	private void setQrCode(String qrCode) {
		QrCode = qrCode;
	}
	
	public void decode(String [] StudentIdQuizId) throws QrFailedToDetect,UnknownHostException
	{
		StringBuffer response = new StringBuffer();
		String StudentId = "",QuizId="";
		for(int i=0;i<15;i++)
			StudentId+=StudentIdQuizId[i];
		for(int i=15;i<19;i++)
			QuizId+=StudentIdQuizId[i];
		
		//QuizId = Integer.valueOf(QuizId).toString(); 
		
		try {
			
			//URL url = new URL("http://labs14.teletaaleem.com/lms271/local/wstemplate/client/client.php");
			URL url=new URL("http://edms-lms.teletaaleem.com/local/wstemplate/client/client.php");
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");	
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			//StudentId="481163157211088";
		    //QuizId="0003";
			String urlParameters = "mapstr="+StudentId+"-"+QuizId;
				
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			int responseCode = connection.getResponseCode();
			
			System.out.println("Response Code : " + responseCode);
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(connection.getInputStream()));
			String inputLine;
			
			
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println(e.getLocalizedMessage().toString());
             if(e.getMessage().toString().contains("edms-lms.teletaaleem.com"))
            	 throw new UnknownHostException();
		} 
		JsonParser parser=new JsonParser();
		JsonObject object=(JsonObject)parser.parse(response.toString());
		String temp=object.get("qrcodeResp").toString();
		if(temp.isEmpty())
			throw new QrFailedToDetect(null);
		else if(temp.contains("incorrect parameters"))
			throw new QrFailedToDetect(null);
		else
			setQrCode(temp);
		

	}
}
