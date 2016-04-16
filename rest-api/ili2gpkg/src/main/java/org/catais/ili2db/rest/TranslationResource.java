package org.catais.ili2db.rest;

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

//import org.catais.ai.dao.CantonDAO;
//import org.catais.ai.dao.DAOException;
//import org.catais.ai.dao.DAOFactory;
import org.catais.ili2db.model.Translation;

import javax.ws.rs.core.MediaType;

@Path("/translations")
public class TranslationResource {

	// Vars ---------------------------------------------------------------------------------------

//	DAOFactory xanadu2 = DAOFactory.getInstance("xanadu2.jdbc");
//	CantonDAO cantonDAO = xanadu2.getCantonDAO();
//	
    // Actions ------------------------------------------------------------------------------------
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
	public int listCantons() {	
		return 33;
	}

}
