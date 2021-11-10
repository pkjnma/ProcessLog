package com.cs.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cs.pojo.LogEvent;

public class MainProcessor {

	public static void main(String[] args) throws Exception {
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		String fileLocation = null;
		if(args != null && args.length > 0) {
			fileLocation = args[0];
		}else {
			throw new Exception("Insufficient input, please provide absolute path of file.");
		}
		
		Map<String, LogEvent> processMap = new ConcurrentHashMap<String, LogEvent>();
		File file = new File(fileLocation);
		FileReader fileReader = null;
		LineNumberReader lineByLineReader = null;
		try {
			fileReader = new FileReader(file);
			lineByLineReader = new LineNumberReader(fileReader);
			
			LogFileReader reader1 = new LogFileReader(processMap, lineByLineReader);
			executorService.execute(reader1);
			executorService.shutdown();
			executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException("File not found at specified path.");
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new Exception("Process is taking longer than expect.");
		}finally {
			lineByLineReader.close();
			fileReader.close();
		}
		
		

	}

}
