package com.test.util;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;

public class MongoUtil {
	
	private static String dbIp = "120.77.169.190";
	private static int dbPort = 27017;
	private static String mydb= "hang";

	public static AdvancedDatastore getDataStore(){
		Morphia morphia = new Morphia();
		MongoClient mongoClient = new MongoClient(dbIp, dbPort);
		Datastore datastore = morphia.createDatastore(mongoClient, mydb);
		return (AdvancedDatastore) datastore;
	}
}
