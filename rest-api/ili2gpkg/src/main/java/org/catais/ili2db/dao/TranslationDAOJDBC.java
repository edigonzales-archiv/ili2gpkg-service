package org.catais.ili2db.dao;

import static org.catais.ili2db.dao.DAOUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.catais.ili2db.model.Translation;

/**
 * This class represents a concrete JDBC implementation of the {@link CantonDAO} interface.
 *
 * @author BalusC
 * @link http://balusc.blogspot.com/2008/07/dao-tutorial-data-layer.html
 */
public class TranslationDAOJDBC implements TranslationDAO {

	// Constants ----------------------------------------------------------------------------------

	private static final String DB_TABLE_TRANSLATION = "ili2gpkg_service.translation";
	
	private static final String SQL_LIST_ALL_TRANSLATIONS =
			"SELECT ogc_fid, file_path, upload_date, download_url FROM " + DB_TABLE_TRANSLATION + " "
					+ "WHERE download_url IS NOT NULL ORDER BY upload_date";
					//+ "ORDER BY upload_date";
	
//	private static final String SQL_LIST_SINGLE =
//			"SELECT ogc_fid, fosnr, code, aname, email, activated FROM " + DB_TABLE + " "
//					+ "WHERE activated = true AND code =  ?";	
//	private static final String SQL_COUNT =
//			"SELECT count(*) FROM " + DB_TABLE + " WHERE activated = TRUE";	
////	private static final String SQL_INSERT =
////			"INSERT INTO User (email, password, firstname, lastname, birthdate) VALUES (?, MD5(?), ?, ?, ?)";
//	private static final String SQL_UPDATE_ACTIVATE =
//			"UPDATE " + DB_TABLE + " SET activated = TRUE, email = ? WHERE code = ?";
//	private static final String SQL_UPDATE_CANTON =
//			"UPDATE " + DB_TABLE + " SET email = ? WHERE code = ? AND activated = TRUE";
//	private static final String SQL_UPDATE_DEACTIVATE =
//			"UPDATE " + DB_TABLE + " SET activated = FALSE WHERE code = ?";

	// Vars ---------------------------------------------------------------------------------------

	private DAOFactory daoFactory;

	// Constructors -------------------------------------------------------------------------------

	/**
	 * Construct an Canton DAO for the given DAOFactory. Package private so that it can be constructed
	 * inside the DAO package only.
	 * @param daoFactory The DAOFactory to construct this Canton DAO for.
	 */
	TranslationDAOJDBC(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	// Actions ------------------------------------------------------------------------------------

	@Override
	public List<Translation> listTranslations() {
		List<Translation> translations = new ArrayList<>();

		try (
				Connection connection = daoFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_LIST_ALL_TRANSLATIONS);
				ResultSet resultSet = statement.executeQuery();
			) 
		{
			while (resultSet.next()) {
				translations.add(map(resultSet));				
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return translations;	
	}
	
//	@Override
//	public void activateCanton(Canton canton) {
//		if (canton.getCode() == null) {
//			throw new IllegalArgumentException("Canton is not activated yet, the canton code is null.");
//		}
//
//		Object[] values = {
//				canton.getEmail(),
//				canton.getCode()
//		};
//		
//		try (
//				Connection connection = daoFactory.getConnection();
//        		PreparedStatement statement = prepareStatement(connection, SQL_UPDATE_ACTIVATE, false, values);
//        	) 
//        {
//        	int affectedRows = statement.executeUpdate();
//        	if (affectedRows == 0) {
//        		throw new DAOException("Updating canton failed, no rows affected.");
//        	}
//        } catch (SQLException e) {
//        	throw new DAOException(e);
//        }
//	}
//	
//	@Override
//	public Canton listCanton(String cantonCode) {
//		Canton canton = null;
//		
//		Object[] values = {
//				cantonCode
//		};
//		
//		try (
//				Connection connection = daoFactory.getConnection();
//				PreparedStatement statement = prepareStatement(connection, SQL_LIST_SINGLE, false, values);
//				ResultSet resultSet = statement.executeQuery();
//			) 
//		{
//			// Should return only one row.
//			while (resultSet.next()) {
//				canton = map(resultSet);
//			}
//		} catch (SQLException e) {
//			throw new DAOException(e);
//		}
//
//		return canton;
//	}
//	
//	@Override
//	public void updateCanton(Canton canton) {
//		if (canton.getCode() == null) {
//			throw new IllegalArgumentException("Canton property is mandatory. Canton cannot be updated.");
//		}
//
//		Object[] values = {
//				canton.getEmail(),
//				canton.getCode()
//		};
//		
//		try (
//				Connection connection = daoFactory.getConnection();
//        		PreparedStatement statement = prepareStatement(connection, SQL_UPDATE_CANTON, false, values);
//        	) 
//        {
//        	int affectedRows = statement.executeUpdate();
//        	if (affectedRows == 0) {
//        		throw new DAOException("Updating canton failed, no rows affected.");
//        	}
//        } catch (SQLException e) {
//        	throw new DAOException(e);
//        }
//	}
//	
//	@Override
//	public void deleteCanton(String cantonCode, boolean recursive) {
//		// 1. Does canton exists (and is activated)?
//		
//		
//		// 2. If recursive == false, does canton have corresponding models?
//		
//		
//		// 3. Delete/deactivate canton.
//		//    If recursive == true, delete all corresponding models.
//		
//		// At the moment just deactivate canton:
//		if (cantonCode == null) {
//			throw new IllegalArgumentException("The canton code is null.");
//		}
//
//		Object[] values = {
//				cantonCode
//		};
//		
//		try (
//				Connection connection = daoFactory.getConnection();
//        		PreparedStatement statement = prepareStatement(connection, SQL_UPDATE_DEACTIVATE, false, values);
//        	) 
//        {
//        	int affectedRows = statement.executeUpdate();
//        	if (affectedRows == 0) {
//        		throw new DAOException("Updating canton failed, no rows affected.");
//        	}
//        } catch (SQLException e) {
//        	throw new DAOException(e);
//        }
//
//	}


	// Helpers ------------------------------------------------------------------------------------

	/**
	 * Map the current row of the given ResultSet to a Translation.
	 * @param resultSet The ResultSet of which the current row is to be mapped to a Translation.
	 * @return The mapped Translation from the current row of the given ResultSet.
	 * @throws SQLException If something fails at database level.
	 */
	private static Translation map(ResultSet resultSet) throws SQLException {
		Translation translation = new Translation();
		translation.setId(resultSet.getInt("ogc_fid"));
		translation.setFilePath(resultSet.getString("file_path"));
		translation.setUploadDate(resultSet.getTimestamp("upload_date"));
		translation.setDownloadUrl(resultSet.getString("download_url"));
		return translation;
	}

}

