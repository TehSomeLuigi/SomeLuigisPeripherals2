package trs.someluigi.slperiph.http;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

import trs.org.simpleframework.http.Cookie;
import trs.org.simpleframework.http.Path;
import trs.org.simpleframework.http.Request;
import trs.org.simpleframework.http.Response;
import trs.org.simpleframework.http.core.Container;
import trs.org.simpleframework.http.core.ContainerServer;
import trs.org.simpleframework.transport.Server;
import trs.org.simpleframework.transport.connect.Connection;
import trs.org.simpleframework.transport.connect.SocketConnection;
import trs.someluigi.slperiph.struct.ListeningComputer;
import trs.someluigi.slperiph.struct.PendingRequest;
import dan200.computer.api.IComputerAccess;

public class HTTPHandler implements Container {
	
	public static Connection conn;
	
	public static HashMap<Integer, ListeningComputer> openComputers = new HashMap<Integer, ListeningComputer>();
	
	public static ListeningComputer getOCByICA(IComputerAccess ica) {
		return openComputers.get(ica.getID());
	}
	
	public static void start(int port) {
		try {
			System.out.println("[SLP2] Starting the HTTP Server.");
			Container cont = new HTTPHandler();
			Server serv = new ContainerServer(cont);
			
			conn = new SocketConnection(serv);
			SocketAddress saddr = new InetSocketAddress(port);
			
			conn.connect(saddr);
		} catch (Exception e) {
			System.out.println("[SLP2] Got an error when loading up the HTTP Server");
			e.printStackTrace();
		}
	}
	
	public static void stop() {
		try {
			System.out.println("[SLP2] Stopping the HTTP Server.");
			conn.close();
			
			openComputers.clear();
		} catch (Exception e) {
			System.out.println("[SLP2] Got an error when stopping the HTTP Server. The server may have stopped anyway. Chances are it did, so this shouldn't be anything to worry about.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(Request req, Response resp) {
		// we received a request
		// need to figure out which computer to send it to
		try {
			Path p = req.getPath();
			PrintStream ps = resp.getPrintStream();
			long time = System.currentTimeMillis();
			String service;
			
			resp.setValue("Server", "SomeLuigisPeripherals2");
			resp.setDate("Date", time);
			
			if (p.getSegments().length >= 1) {
				service = p.getSegments()[0];
				
				try {
					int serviceId = Integer.parseInt(service);
					
					ListeningComputer lc = HTTPHandler.openComputers.get(serviceId);
					
					if (lc != null) {
						PendingRequest pr = new PendingRequest();
						pr.path = p;
						pr.req = req;
						pr.resp = resp;
						
						
						int requestId = 0;
						
						// find a free request Id
						while (true) {
							if (! lc.reqs.containsKey(requestId)) {
								break;
							}
							
							++requestId;
						}
						
						
						lc.reqs.put(requestId, pr);
						
						Map<String, String> cookies = new HashMap<String, String>();
                        
                        for (Cookie c: pr.resp.getCookies()) {
                            cookies.put(c.getName(), c.getValue());
                        }
						
						String path;
						
						if (p.getSegments().length > 1) {
							path = p.getPath(1);
						} else {
							path = "/";
						}
						
						lc.ica.queueEvent("http_server_request", new Object[] { requestId, path, pr.req.getQuery(), cookies });
					} else {
						resp.setValue("Content-type", "text/html");
						
						ps.println("<!DOCTYPE html>");
						ps.println("<html>");
						ps.println("<head>");
						ps.println("<title>SLP</title>");
						ps.println("</head><body>");
						ps.println("<h2>SomeLuigis Peripherals 2 - HTTP Server Module</h2><br>The computer/turtle specified is not online.<br>Please enter the ID of a computer or turtle: <input type='text' id='comid'>, then click <input type='button' onClick='location = \"/\" + document.getElementById(\"comid\").value;' value='here'>.");
						ps.println("</body></html>");
						resp.close();
					}
				} catch (NumberFormatException e) {
					resp.setValue("Content-type", "text/html");
					
					ps.println("<!DOCTYPE html>");
					ps.println("<html>");
					ps.println("<head>");
					ps.println("<title>SLP</title>");
					ps.println("</head><body>");
					ps.println("<h2>SomeLuigis Peripherals 2 - HTTP Server Module</h2><br>The ID you entered for a computer/turtle could not be parsed as integer/number.<br>Please enter the ID of a computer or turtle: <input type='text' id='comid'>, then click <input type='button' onClick='location = \"/\" + document.getElementById(\"comid\").value;' value='here'>.");
					ps.println("</body></html>");
					resp.close();
				}
				
				
				
				
			} else {
				resp.setValue("Content-type", "text/html");
				
				ps.println("<!DOCTYPE html>");
				ps.println("<html>");
				ps.println("<head>");
				ps.println("<title>SLP</title>");
				ps.println("</head><body>");
				ps.println("<h2>SomeLuigis Peripherals 2 - HTTP Server Module</h2><br>Please enter the ID of a computer or turtle: <input type='text' id='comid'>, then click <input type='button' onClick='location = \"/\" + document.getElementById(\"comid\").value;' value='here'>.");
				ps.println("</body></html>");
				resp.close();
			}
			
			
			
		} catch (Exception e) {
			System.err.println("[SLP2] Got an error when handling a HTTP Request.");
			e.printStackTrace();
		}
	}

}
