package proba.java.order;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStream;
import java.util.Properties;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


public class Functions {
	
	public void conf (InputStream input, Properties Config) {
		try {

			input = new FileInputStream("src/proba/java/order/config.properties");

			// load a properties file
			Config.load(input);
			


		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Config file loaded");
		}
	}
	
	
	
	
	//integer-e
	public static String isInteger (String str)			//return OK, ha int
	{  
		 try  
		  {  	
		    int d = Integer.parseInt(str);
		    return "OK";
		  }  
		  catch(NumberFormatException nfe)  
		  {  
			  return nfe.toString(); 
		  }  
	    
	}
	
	//email-e
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static String isEmail (String str) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(str);
		        if (matcher.find()==true) {
		        	return "OK";
		        }
				return "Invalid email format";
		}
	
	
	//megfelelo ar-e
	public static String isValidPrice (String str, double i) {
		if (isDouble(str) == "OK") {
			if (Double.parseDouble(str) >= i) {
				return "OK";
			}
			else {
				return "Less than minimum price";
			}
		}
		else {
			return "Not double";
		}
	}
	
	
	//double-e
	public static String isDouble (String str)
	{  
	  try  
	  {  
	    Double d = Double.parseDouble(str);
	    return "OK";
	  }  
	  catch(NumberFormatException nfe)  
	  {  
		  return nfe.toString(); 
	  }  
	}

	//megfelelo-e a status
	public static String isStatus (String str) {
		
		if (str.trim().equals("IN_STOCK") || str.trim().equals("OUT_OF_STOCK")) {
			return "OK";
		} else {
			return "jds";
		}
		
		}

	
	//datum check
	public static String isDate (String str) {
			if (str.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
				return "OK";
				}
				
			else {
				return "NEMOK";
			}
	}
	
	

	//sor oszlopainak formatumat ellenorzi
	public static void validate (String[] asd, String[] resp){
		
		for(int i = 0; i < asd.length ; i++) {
//			System.out.println("InpLine[" + i + "]" +InpLine[i]);
			switch (i) {
			
			case 0:  	resp[0]= asd[i]	;							//LineNumber
						resp[2]= "";
						break;
						
            case 1: if (isInteger(asd[i]) != "OK") {				//OrderIId
            			resp[2]= resp[2] + "OrderId not int or missing, ";
            			resp[1] = "Error";
			         }
			        else {
			           	resp[2]= resp[2] + "";	
			        };
            		break;
            			
            case 2: if (isInteger(asd[i]) != "OK") {				//OrderItemId
            				resp[2]= resp[2] + "OrderItemId not int or missing, ";
            				resp[1] = "Error";
			            }
			            else {
			            	resp[2]= resp[2] + "";	
			            }
                     break;
                     
            case 3: if (asd[i].isEmpty()) {					//BuyerName	
		            	resp[2]= resp[2] + "BuyerName missing, ";
		            	resp[1] = "Error";
							}
		            else {
		            	resp[2]= resp[2] + "";	
		            }
                     break;
            case 4: if (isEmail(asd[i]) != "OK") {					//BuyerEmail
						resp[2]= resp[2] + "BuyerEmail not valid or missing, ";
						resp[1] = "Error";
					}	
		            else {
		            	resp[2]= resp[2] + "";	
		            }
            		break;
            		
            case 5: if (asd[i].isEmpty()) {					//Address
							resp[2]= resp[2] + "Address missing, ";
							resp[1] = "Error";
						}
		            else {
			            	resp[2]= resp[2] + "";
			            }
                     break;
                     
            case 6: if (isInteger(asd[i]) == "OK") {				//Postcode
							resp[2]= resp[2] + "";
						}
		            else {
		            	resp[2]= resp[2] + "Postcode not int or missing, ";	
		            	resp[1] = "Error";
	            	}
                    break;
                    
            case 7:  if (isValidPrice(asd[i], 1.00) == "Less than minimum price") {				//SalePrice
							resp[2]= resp[2] + "SalePrice less than minimum price, ";
						}
            		else if (isValidPrice(asd[i], 1.00) == "Not double") {
            			resp[2]= resp[2] + "SalePrice not double or missing, ";
            			resp[1] = "Error";
            			}
            		else {
		            	resp[2]= resp[2] + "";	
	            	}
                     break;
                     
            case 8:  if (isValidPrice(asd[i], 0.00) == "Less than minimum price") {				//ShippingPrice
							resp[2]= resp[2] + "ShippingPrice less than minimum price, ";
							resp[1] = "Error";
						}
					else if (isValidPrice(asd[i], 0.00) == "Not double") {
						resp[2]= resp[2] + "ShippingPrice not double or missing, ";
						resp[1] = "Error";
						}
					else {
		            	resp[2]= resp[2] + "";	
	            	}
                     break;
                     
            case 9: if (asd[i].isEmpty()) {					//SKU
							resp[2]= resp[2] + "SKU is missing, ";
							resp[1] = "Error";
						}	
		            else {
		            	resp[2]= resp[2] + "";	
		        	}									
                     break;
                     
            case 10: if (isStatus(asd[i].trim()) == "OK") {					//Status	
	            	resp[2]= resp[2] + "";
						}
		            else {
						resp[2]= resp[2] + "Status is not valid or missing, ";
		            	resp[1] = "Error";
		        	}
                     break;
                     
            case 11: if (asd[i].isEmpty()) {						//OrderDate
		            	LocalDate localDate = LocalDate.now();						//ha a datum mezo ures, beletesszuk a mai datumot
		            	asd[i] = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate).toString();
		            				}
		             else {											//ha nem ures, megnezzuk, jo-e a formatuma
		            	if (isDate(asd[i]) == "OK") {	
							resp[2]= resp[2] + "";
							
							}	
			            else {
			            	resp[2]= resp[2] + "OrderDate is not valid, ";	
			            	resp[1] = "Error";
			        		}
		             	}
            			break;
        }

		}
		
		
	}
	
	
	
	public static void append (String[] respl, BufferedWriter writer) throws IOException{
		
		for (int i = 0; i< 3; i++) {
			
			    if (i == 0 || i == 1) {
			    	writer.append(respl[i]);
			    	writer.append(";");	
			    }
			    else {
			    	writer.append(respl[i]);
			    	writer.append("\n");
			    }
		}
			   
			}
	
	
	
//	 DB connecttion
	public static Connection Connect(Properties Config){
		System.out.println("Connecting to database");
		try {
			String driver = Config.getProperty("DatabaseDriver");
			String url = Config.getProperty("DatabaseURL");
			Class.forName(driver);
			
			Connection conn =DriverManager.getConnection(url,Config.getProperty("DatabaseUserName"),"");
			System.out.println("Connected");
			return conn;
		}
		catch (Exception e){
			System.out.println("Connection error " + e);
			return null;
		}	
		
		
		}
	
	
	//nem duplicate Idk felvitete az adatbazisba
	public static void update(Connection conn, String inpl[], String respl[]) {
		 double sale = Double.parseDouble(inpl[7]);
		 double shipping = Double.parseDouble(inpl[8]);
		 double total = sale+shipping;
		    try
		    {
		   
		      Statement st = conn.createStatement();
		      if ( st.executeUpdate("INSERT INTO order_item VALUES ('"+Integer.parseInt(inpl[1])+"', '"+Integer.parseInt(inpl[2])+"','"+inpl[7]+"','"+inpl[8]+"','"+total+"','"+inpl[9]+"','"+inpl[10]+"')") != 0) {
		    	  st.executeUpdate("INSERT INTO order_java VALUES ('"+Integer.parseInt(inpl[2])+"', '"+inpl[3]+"','"+inpl[4]+"','"+inpl[11]+"','"+total+"','"+inpl[5]+"','"+inpl[6]+"')");
			      respl[1]="OK";
		      }
		      else {
		    	  respl[2]=respl[2] + "OrderId or OrderItemId already in use";
		      }

		    }
		    catch (SQLException ex)
		    { 
		    	respl[1]="Error";
		    	respl[2]=respl[2] + "OrderId or OrderItemId already in use";
		      System.err.println(ex.getMessage());
		    }
	}
	
	
	
	public static void ftp(Properties Config) {
		 System.out.println("Connecting to FTP");
		FTPClient client = new FTPClient();
		FileInputStream fis = null;
		try {
		    client.connect(Config.getProperty("FTPURL"));
		    client.login(Config.getProperty("FTPUserName"), Config.getProperty("FTPPassword"));
		    System.out.println("FTP connection established");
		    //
		    // Create an InputStream of the file to be uploaded
		    //
		    String filename = Config.getProperty("ResponeFileNameLocal");
		    fis = new FileInputStream(filename);

		    //
		    // Store file to server
		    //
		    client.storeFile(Config.getProperty("FTPResponseFileName"), fis);
		    System.out.println("Response file uploaded");
		    client.logout();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (fis != null) {
		            fis.close();
		        }
		        client.disconnect();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
		
	}

