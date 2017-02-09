package ca.dal;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table(value = "cd_1")
public class CrimeData {

	@PrimaryKeyColumn(name="year_mod3_hash",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
	int year_mod3_hash;
	
	@PrimaryKeyColumn(name="year",ordinal = 0,type = PrimaryKeyType.CLUSTERED)
	int year;
	
	@PrimaryKeyColumn(name="dc_dist",ordinal = 0,type = PrimaryKeyType.CLUSTERED)
	int dc_dist;
	
	@PrimaryKeyColumn(name="dc_key",ordinal = 0,type = PrimaryKeyType.CLUSTERED)
	Long dcKey;
	
	@Column(value = "dispatch_date")
	String dispatch_date; 
	
	@Column(value = "dispatch_date_time")
	String dispatch_date_time;
	
	@Column(value = "dispatch_time")
	String dispatch_time ; 
	
	@Column(value = "hour")
	int hour;
	
	@Column(value = "lat")
String lat;
	
	@Column(value = "location_block")
	String location_block; 
	
@Column(value = "lon")
	String lon ;
	
	@Column(value = "month")
	String month; 
	
	@Column(value = "police_districts")
	String police_districts;
	
	@Column(value = "psa")
	String psa;
	
	@Column(value = "text_general_code")
	String text_general_code;
	
	@Column(value = "ucr_general")
	String ucr_general ;

	public int getYear_mod3_hash() {
		return year_mod3_hash;
	}

	public void setYear_mod3_hash(int year_mod3_hash) {
		this.year_mod3_hash = year_mod3_hash;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDcKey() {
		return dcKey;
	}

	public void setDcKey(Long dc_key) {
		this.dcKey = dc_key;
	}

	public String getDispatch_date() {
		return dispatch_date;
	}

	public void setDispatch_date(String dispatch_date) {
		this.dispatch_date = dispatch_date;
	}

	public String getDispatch_date_time() {
		return dispatch_date_time;
	}

	public void setDispatch_date_time(String dispatch_date_time) {
		this.dispatch_date_time = dispatch_date_time;
	}

	public String getDispatch_time() {
		return dispatch_time;
	}

	public void setDispatch_time(String dispatch_time) {
		this.dispatch_time = dispatch_time;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLocation_block() {
		return location_block;
	}

	public void setLocation_block(String location_block) {
		this.location_block = location_block;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPolice_districts() {
		return police_districts;
	}

	public void setPolice_districts(String police_districts) {
		this.police_districts = police_districts;
	}

	public String getPsa() {
		return psa;
	}

	public void setPsa(String psa) {
		this.psa = psa;
	}

	public String getText_general_code() {
		return text_general_code;
	}

	public void setText_general_code(String text_general_code) {
		this.text_general_code = text_general_code;
	}

	public String getUcr_general() {
		return ucr_general;
	}

	public void setUcr_general(String ucr_general) {
		this.ucr_general = ucr_general;
	}

	public CrimeData() {
	}

	
	public CrimeData(int year_mod3_hash,int year,int dc_dist,Long dc_key ,
			String dispatch_date ,String  dispatch_date_time ,String  dispatch_time, int hour ,
			String lat , String  location_block ,String lon,String  month,
			String  police_districts,String  psa ,String  text_general_code,String  ucr_general) {
			this.year_mod3_hash=year_mod3_hash;
			this.year=year;
			this.dc_dist=dc_dist;
			this.dcKey = dc_key;
			this.dispatch_date =dispatch_date;
			this.dispatch_date_time =dispatch_date_time;
			this.dispatch_time=dispatch_time;
			this.hour =hour;
			this.lat =lat;
			this.location_block = location_block;
			this.lon=lon;
			this.month=month;
			this.police_districts=police_districts;
			this.psa =psa;
			this.text_general_code=text_general_code;
			this.ucr_general=ucr_general;
	}

	
	@Override
	public String toString() {
		return String.format("CrimeData[year_mod3_hash=%s, year='%s', dc_dist='%s' , "
				+ "dc_key='%s', dispatch_date='%s', dispatch_date_time='%s', dispatch_time='%s'"
				+ "hour='%s',lat='%s',location_block='%s',lon='%s',month='%s'"
				+ "police_districts='%s',psa='%s',text_general_code='%s',ucr_general='%s']",
				this.year_mod3_hash,
				this.year,
				this.dc_dist,
				this.dcKey ,
				this.dispatch_date ,
				this.dispatch_date_time ,
				this.dispatch_time,
				this.hour ,
				this.lat ,
				this.location_block ,
				this.lon,
				this.month,
				this.police_districts,
				this.psa ,
				this.text_general_code,
				this.ucr_general);
	}
}
