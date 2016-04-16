package org.catais.ili2db.rest;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

import org.catais.ili2db.dao.TranslationDAO;
import org.catais.ili2db.dao.DAOException;
import org.catais.ili2db.dao.DAOFactory;
import org.catais.ili2db.model.Translation;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.MediaType;

@Path("/translations")
public class TranslationResource {

	// Vars ---------------------------------------------------------------------------------------

	DAOFactory xanadu2 = DAOFactory.getInstance("xanadu2.jdbc");
	TranslationDAO translationDAO = xanadu2.getTranslationDAO();
	
    // Actions ------------------------------------------------------------------------------------
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public List<Translation> listTranslations() {	
		return translationDAO.listTranslations();
	}
	
	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadInterlisFile(@FormDataParam("file") InputStream fileInputStream, 
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		
		
		
		
		return Response.ok("Data uploaded successfully !!").build();
		
	}
}
