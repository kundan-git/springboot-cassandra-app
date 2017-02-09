package ca.dal;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.dal.CrimeData;

@RestController
@ComponentScan(basePackages="ca.dal")
public class MainController {

	@Autowired
	private CassandraOperations cassandraTemplate;

	@RequestMapping("/hello")
	public String hello(){
		return "Hello";
	}

	@RequestMapping(value = "/dc_key",method = RequestMethod.GET)
	@ResponseBody
	public CrimeData dcKey() {
		Select sel = QueryBuilder.select().from("cd_1");
		sel.where(QueryBuilder.eq("year_mod3_hash",2)).and
		(QueryBuilder.eq("year", 2009)).and
		(QueryBuilder.eq("dc_dist", 19)).and
		(QueryBuilder.eq("dc_key", 200919045006L));

		System.out.println(sel.toString());

		CrimeData result = cassandraTemplate.selectOne(sel, CrimeData.class);
		return result;
	}


	/**
	 * For a year, for a district, for a month, fetch all crime records.
	 * select * from cd_1 where year_mod3_hash=2 AND year= 2009 AND dc_dist=19 AND month='2009-05';
	 * http://localhost:8080/phil_crime_data/records/year/2009/month/2009-05/district/19
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/records/year/{year}/month/{month}/district/{district}",method = RequestMethod.GET)
	@ResponseBody
	public HashMap getRecordForAMonth(@PathVariable int year,@PathVariable String month, @PathVariable int district) {
		@SuppressWarnings("rawtypes")
		HashMap resp = new HashMap();
		try
		{
			int hash = year%3; 
			Select sel = QueryBuilder.select().from("cd_1");
			sel.where(QueryBuilder.eq("year_mod3_hash",hash)).and
			(QueryBuilder.eq("year", year)).and
			(QueryBuilder.eq("dc_dist", district)).and
			(QueryBuilder.eq("month",month));

			System.out.println(sel.toString());
			
			long start = System.currentTimeMillis( );
			List<CrimeData> result = cassandraTemplate.select(sel,CrimeData.class);
			long end = System.currentTimeMillis( );
			long diff = end - start;
			resp.put("cassandra_response_time_msec", diff);
			
			if(result.size() > 0){
				resp.put("status", "200");
				resp.put("result", result);
				resp.put("message", "OK");
			}else{
				resp.put("status", "404");
				resp.put("message", "No result found!");
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println(e.getStackTrace().toString());
			resp.put("status", "500");
			resp.put("message", "Server Error!");
		}
		return resp;
	}

	/**
	 * For a year, for a district, for a month, fetch all records for a particular UCR_General.
	 * select * from cd_1 where year_mod3_hash=2 AND year= 2009 AND dc_dist=19 AND month='2009-05' AND ucr_general='800' ALLOW FILTERING;
	 * http://localhost:8080/phil_crime_data/records/year/2009/month/2009-05/district/19/ucr_general/800
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/records/year/{year}/month/{month}/district/{district}/ucr_general/{ucr_general}",method = RequestMethod.GET)
	@ResponseBody
	public HashMap getRecordForAUcrGeneral(@PathVariable int year,@PathVariable String month,
			@PathVariable int district,@PathVariable String ucr_general) {
		@SuppressWarnings("rawtypes")
		HashMap resp = new HashMap();
		try
		{
			int hash = year%3; 
			Select sel = QueryBuilder.select().from("cd_1");
			sel.where(QueryBuilder.eq("year_mod3_hash",hash)).and
			(QueryBuilder.eq("year", year)).and
			(QueryBuilder.eq("dc_dist", district)).and
			(QueryBuilder.eq("month",month)).and
			(QueryBuilder.eq("ucr_general",ucr_general));
			sel.allowFiltering();

			System.out.println(sel.toString());
			
			long start = System.currentTimeMillis( );
			List<CrimeData> result = cassandraTemplate.select(sel,CrimeData.class);
			long end = System.currentTimeMillis( );
			long diff = end - start;
			resp.put("cassandra_response_time_msec", diff);
			
			if(result.size() > 0){
				resp.put("status", "200");
				resp.put("result", result);
				resp.put("message", "OK");
			}else{
				resp.put("status", "404");
				resp.put("message", "No result found!");
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println(e.getStackTrace().toString());
			resp.put("status", "500");
			resp.put("message", "Server Error!");
		}
		return resp;
	}


	/**
	 * Fetch a record by Dc_Key.
	 * select * from cd_1 where year_mod3_hash=2 AND year= 2009 AND dc_dist=19 AND dc_key=200919045006;
	 * http://localhost:8080/phil_crime_data/records/200919045006
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/records/{dc_key}",method = RequestMethod.GET)
	@ResponseBody
	public HashMap getRecordByDcKey(@PathVariable Long dc_key) {
		@SuppressWarnings("rawtypes")
		HashMap resp = new HashMap();
		try
		{
			String dcAsString = dc_key.toString();

			if(dcAsString.length() > 6){
				int year =  Integer.parseInt(dcAsString.substring(0, 4));
				int dist = Integer.parseInt(dcAsString.substring(4, 6));			 
				int hash = year%3; 
				Select sel = QueryBuilder.select().from("cd_1");
				sel.where(QueryBuilder.eq("year_mod3_hash",hash)).and
				(QueryBuilder.eq("year", year)).and
				(QueryBuilder.eq("dc_dist", dist)).and
				(QueryBuilder.eq("dc_key", dc_key.longValue()));

				System.out.println(sel.toString());
				long start = System.currentTimeMillis( );
				CrimeData result = cassandraTemplate.selectOne(sel, CrimeData.class);
				long end = System.currentTimeMillis( );
				long diff = end - start;
				resp.put("cassandra_response_time_msec", diff);
				
				if(result != null){
					resp.put("status", "200");
					resp.put("result", result);
					resp.put("message", "OK");
				}else{
					resp.put("status", "404");
					resp.put("message", "No result found!");
				}
			}

		}catch(Exception e){
			System.out.println(e);
			System.out.println(e.getStackTrace().toString());
			resp.put("status", "500");
			resp.put("message", "Server Error!");
		}
		return resp;
	}
	
	
	/**
	 * For a year, for a district, for a Psa, find all  records.
	 * select * from cd_1 where year_mod3_hash=2 AND year= 2009 AND dc_dist=19 AND psa='A'  ALLOW FILTERING;
	 * http://localhost:8080/phil_crime_data/records/year/2009/district/19/psa/A
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/records/year/{year}/district/{district}/psa/{psa}",method = RequestMethod.GET)
	@ResponseBody
	public HashMap getRecordForPsa(@PathVariable int year, @PathVariable int district,@PathVariable String psa) {
		@SuppressWarnings("rawtypes")
		HashMap resp = new HashMap();
		try
		{
			int hash = year%3; 
			Select sel = QueryBuilder.select().from("cd_1");
			sel.where(QueryBuilder.eq("year_mod3_hash",hash)).and
			(QueryBuilder.eq("year", year)).and
			(QueryBuilder.eq("dc_dist", district)).and
			(QueryBuilder.eq("psa",psa));
			sel.allowFiltering();

			System.out.println(sel.toString());
			
			long start = System.currentTimeMillis( );
			List<CrimeData> result = cassandraTemplate.select(sel,CrimeData.class);
			long end = System.currentTimeMillis( );
			long diff = end - start;
			resp.put("cassandra_response_time_msec", diff);
			
			if(result.size() > 0){
				resp.put("status", "200");
				resp.put("result", result);
				resp.put("message", "OK");
			}else{
				resp.put("status", "404");
				resp.put("message", "No result found!");
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println(e.getStackTrace().toString());
			resp.put("status", "500");
			resp.put("message", "Server Error!");
		}
		return resp;
	}


}
