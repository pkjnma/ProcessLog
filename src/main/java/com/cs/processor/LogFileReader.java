package com.cs.processor;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cs.entity.LogEntity;
import com.cs.hibernate.util.HibernateUtil;
import com.cs.pojo.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogFileReader implements Runnable {
	private LineNumberReader lineByLineReader;
	private Map<String, LogEvent> processMap;

	public LogFileReader(Map<String, LogEvent> pMap, LineNumberReader lineByLineReader) {
		this.processMap = pMap;
		this.lineByLineReader = lineByLineReader;
	}

	public void readAndProcessFile() throws IOException {
		String strLine = "";
		LogEvent logEvent = null;
		synchronized (this) {
			while ((strLine = lineByLineReader.readLine()) != null) {
				logEvent = new ObjectMapper().readValue(strLine, LogEvent.class);
				if (processMap.containsKey(logEvent.getId())) {
					long diff = 0;
					boolean alert = false;
					if(logEvent.getState().equals("FINISHED")) {
						diff = logEvent.getTimestamp().getTime()-processMap.get(logEvent.getId()).getTimestamp().getTime();
					}else {
						diff = processMap.get(logEvent.getId()).getTimestamp().getTime()-logEvent.getTimestamp().getTime();
					}
					if(diff>4) {
						alert = true;
					}
					LogEvent le = processMap.get(logEvent.getId());
					le.setAlert(alert);
					le.setDuration(diff);
				} else {
					processMap.put(logEvent.getId(), logEvent);
				}
			}
		}
	}

	public void run() {
		try {
			readAndProcessFile();
			synchronized (this) {
				for(String eventId : processMap.keySet()) {
					saveEntityLog(new LogEntity(processMap.get(eventId)));
					processMap.remove(eventId);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveEntityLog(LogEntity logEntity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.save(logEntity);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

}
