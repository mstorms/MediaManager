

public class MediaInfo {
	
	private String title, comments, mediaType, genre;
	private int year, id;
	private double currentVal;
	
	/**
	 * Constructor, given no info
	 */
	public MediaInfo() {
		id = 0;
		title = "";
		year = 0;
		genre = "";
		mediaType = "";
		comments = "";
		currentVal = 0;
	}
	
	/**
	 * Constructor, given all info
	 */
	public MediaInfo(int id, String title, int year, String genre, String mediaType, String comments, double currentVal) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.mediaType = mediaType;
		this.comments = comments;
		this.currentVal = currentVal;
	}
	
	/**
	 * Constructor, given all info except ID
	 */
	public MediaInfo(String title, int year, String genre, String mediaType, String comments, double currentVal) {
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.mediaType = mediaType;
		this.comments = comments;
		this.currentVal = currentVal;
	}
	
	/**
	 * Set ID
	 */
	public void setId(int i) {
		this.id = i;
	}
	
	/**
	 * Set title
	 */
	public void setTitle(String s) {
		this.title = s;
	}
	
	/**
	 * Set year
	 */
	public void setYear(int i) {
		this.year = i;
	}
	
	/**
	 * Set genre
	 */
	public void setGenre(String s) {
		this.genre = s;
	}
	
	/**
	 * Set mediatype
	 */
	public void setMediaType(String s) {
		this.mediaType = s;
	}
	
	/**
	 * Set comments
	 */
	public void setComments(String s) {
		this.comments = s;
	}
	
	/**
	 * Set current value
	 */
	public void setCurrentValue(double d) {
		this.currentVal = d;
	}
	
	/**
	 * Get ID
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Get title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Get year
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * Get genre
	 */
	public String getGenre() {
		return this.genre;
	}
	
	/**
	 * Get mediatype
	 */
	public String getMediaType() {
		return this.mediaType;
	}
	
	/**
	 * Get comments
	 */
	public String getComments() {
		return this.comments;
	}
	
	/**
	 * Get current value
	 */
	public double getCurrentValue() {
		return this.currentVal;
	}
}
