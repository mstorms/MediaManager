import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class MediaDAI {
	
	private ArrayList mediaList;
	
	// Constants
	private final String USER = "XXXX";
	private final String PASS = "XXXXX";
	private final String URL = "jdbc:mysql://localhost:3306/Mysql";
	
	private Connection connect;
	
	/**
	 * Constructor
	 */
	public MediaDAI() {
		mediaList = new ArrayList();
		connect();
	}
	
	/**
	 * Connect to database
	 */
	private Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			connect = DriverManager.getConnection(URL, USER, PASS);
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return connect;
	}
	
	/**
	 * Search media by title
	 */
	public ArrayList searchMedia(String title) {
		try {
			String sql = "SELECT * FROM mediamanager.mediaitem WHERE Name like '%" + title + "%'";

			Statement s = connect.createStatement();

			ResultSet rs = s.executeQuery(sql);

			String mTitle = "";
			String comments = "";
			String sGenre = "";
			String sType = "";
			double currentVal;
			int id, year, genre, mediaType;

			while(rs.next())
			{
				id = rs.getInt("ID");
				mTitle = rs.getString("Name");
				comments = rs.getString("Comments");
				year = rs.getInt("Year");
				genre = rs.getInt("GenreID");
				mediaType = rs.getInt("MediaTypeID");
				currentVal = rs.getDouble("CurrentValue");
				
				sGenre = searchGenreById(genre);
				
				sType = searchTypeById(mediaType);

				MediaInfo media = new MediaInfo(id, mTitle, year, sGenre, sType, comments, currentVal);

				mediaList.add(media);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return mediaList;
	}
	
	/**
	 * Add MediaInfo object to database
	 */
	public void addMedia(MediaInfo mI){
		String genre = mI.getGenre();
		String type = mI.getMediaType();
		String title = mI.getTitle();
		int year = mI.getYear();
		String comments = mI.getComments();
		double currentVal = mI.getCurrentValue();
		
		try {
			int iGenre = 0;
			int iType = 0;
			
			iGenre = searchGenreByName(genre);
			
			// Add genre to database if not already included
			if (iGenre == 0) {
				String sqlGen = "INSERT INTO mediamanager.genre (GenreDescription) VALUES ('" + genre + "')";
				PreparedStatement sGen = connect.prepareStatement(sqlGen);
				sGen.executeUpdate();
				iGenre = searchGenreByName(genre);
			}
			
			iType = searchTypeByName(type);
			
			// Add mediatype to database if not already included
			if (iType == 0) {
				String sqlType = "INSERT INTO mediamanager.mediatype (MediaTypeDescription) VALUES ('" + type + "')";
				PreparedStatement sType = connect.prepareStatement(sqlType);
				sType.executeUpdate();
				iType = searchTypeByName(type);
			}
			
			String sql = "INSERT INTO mediamanager.mediaitem (GenreID, MediaTypeID, Name, Year, Comments, CurrentValue)" + 
							"VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setInt(1, iGenre);
			ps.setInt(2, iType);
			ps.setString(3, title);
			ps.setInt(4, year);
			ps.setString(5, comments);
			ps.setDouble(6, currentVal);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Helper method to find genre description by ID
	 */
	private String searchGenreById(int i) {
		String sGenre = "";
		try {
			String sqlGen = "SELECT * FROM mediamanager.genre WHERE ID like '%" + i + "%'";
			
			Statement sGen = connect.createStatement();
			
			ResultSet rsGen = sGen.executeQuery(sqlGen);
			
			while (rsGen.next()) {
				sGenre = rsGen.getString("GenreDescription");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return sGenre;
	}
	
	/**
	 * Helper method to find genre ID by description
	 */
	private int searchGenreByName(String s) {
		int iGenre = 0;
		try {
			String sqlGen = "SELECT * FROM mediamanager.genre WHERE GenreDescription like '%" + s + "%'";
			
			Statement sGen = connect.createStatement();
			
			ResultSet rsGen = sGen.executeQuery(sqlGen);
			
			while (rsGen.next()) {
				iGenre = rsGen.getInt("ID");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return iGenre;
	}
	
	/**
	 * Helper method to find mediatype description by ID
	 */
	private String searchTypeById(int i) {
		String sType = "";
		try {
			String sqlT = "SELECT * FROM mediamanager.mediatype WHERE ID like '%" + i + "%'";
			
			Statement sT = connect.createStatement();
			
			ResultSet rsT = sT.executeQuery(sqlT);
			
			while (rsT.next()) {
				sType = rsT.getString("MediaTypeDescription");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return sType;
	}
	
	/**
	 * Helper method to find mediatype ID by description
	 */
	private int searchTypeByName(String s) {
		int iType = 0;
		try {
			String sqlT = "SELECT * FROM mediamanager.mediatype WHERE MediaTypeDescription like '%" + s + "%'";
			
			Statement sT = connect.createStatement();
			
			ResultSet rsT = sT.executeQuery(sqlT);
			
			while (rsT.next()) {
				iType = rsT.getInt("ID");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return iType;
	}
}
