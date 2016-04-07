package org.catais.ili2db.rest;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/test/")
public class TestAsyncResponse {

    private static final int SLEEP_TIME_IN_MILLIS = 3000;
    private static final ExecutorService TASK_EXECUTOR = Executors.newCachedThreadPool();

    
    
    
    
	@GET
	@Path("async_v2/{echo}")
	public Response asyncEcho_v2(@PathParam("echo") final String echo) {

		new Thread()
		{
		    public void run() {
		        try {
					Thread.sleep(SLEEP_TIME_IN_MILLIS);
				} catch (final InterruptedException ex) {
					throw new ServiceUnavailableException();
				}	
		        
		        System.out.println("blah");

		    }
		}.start();		
		
		return Response.status(Response.Status.ACCEPTED).build();
	}
    
    
	@GET
	@Path("sync/{echo}")
	public String syncEcho(@PathParam("echo") final String echo) {
		try {
			Thread.sleep(SLEEP_TIME_IN_MILLIS);
		} catch (final InterruptedException ex) {
			throw new ServiceUnavailableException();
		}
		return echo;
	}
	
	
	@GET
	@Path("async/{echo}")
	public Response asyncEcho(@PathParam("echo") final String echo, @Suspended final AsyncResponse ar) {
		TASK_EXECUTOR.submit(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(SLEEP_TIME_IN_MILLIS);
				} catch (final InterruptedException ex) {
					ar.cancel();
				}
				ar.resume(echo);
			}
		});
		
		System.out.println("asdf");
		return Response.status(202).build();

	}
}







//		//ar.setTimeout( 5, TimeUnit.SECONDS );
//		ar.setTimeoutHandler( new MyTimeoutHandler( ) );
//		ar.resume( someWork( ) );
//	}
//	
//	protected String someWork()
//	{
//		try {
//			Thread.sleep(10000);
//			return "suuuuper";
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "gag";
//		}
//	}
//}
//
//class MyTimeoutHandler implements TimeoutHandler
//{
//	@Override
//	public void handleTimeout( AsyncResponse response )
//	{
//		Response r = Response.ok( ).status( HttpURLConnection.HTTP_ACCEPTED ).build( );
//		response.resume( r);
//	}
//}

//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//
//				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//
//				System.out.println("#### thread started: " 
//						+ df.format(new Date()) + " ####");
//				String result = veryExpensiveOperation();
//				System.out.println("#### thread finished: " 
//						+ df.format(new Date()) + " ####");
//
//				response.resume(result);
//			}
//
//			private String veryExpensiveOperation() {
//
//				try {
//
//					Thread.sleep(10000);
//				} 
//				catch (InterruptedException e) {
//
//					e.printStackTrace();
//				}
//
//				return "Woke up!";
//			}
//		}).start();
//
//		return Response.status(202).entity("Request accepted. " + "Long running operation started").build();
//	}


