package com.coviddata.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.coviddata.Model.Covidata;

import jakarta.annotation.PostConstruct;

@Service
public class CoronaVirusTrackerService {
	
	private static String url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    
	private static List<Covidata> finaldatalist=new ArrayList<>();
	
	
	@PostConstruct
	@Scheduled(cron = "* 10 * * * *")
	public void fetchCovidData() throws IOException, InterruptedException {
		
		List<Covidata> newdata=new ArrayList<>();
		
		
		
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		StringReader stringreader = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringreader);
		for (CSVRecord record : records) {
			Covidata data=new Covidata();
			data.setCountry(record.get("Country/Region"));
		    data.setState(record.get("Province/State"));
		    int lastedtotalcases=Integer.parseInt(record.get(record.size()-1));
		    int previouslastedtotalcases=Integer.parseInt(record.get(record.size()-2));
		    data.setLastedTotalCases(lastedtotalcases);
		    data.setDiffrompreviousday(lastedtotalcases-previouslastedtotalcases);
		    
		    newdata.add(data);
		   
		}
		
		this.finaldatalist=newdata;
		
		
	}


	public static List<Covidata> getFinaldatalist() {
		return finaldatalist;
	}
	
	
}
