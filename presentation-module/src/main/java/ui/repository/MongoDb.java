package ui.repository;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public final class MongoDb {
    
    private static final String geometryDBname = "Geometry";
	private static final String geometryCollectionName = "Figures";

    private static MongoDb inst = null;
    private String dbname = null;
    private MongoDatabase database = null;

    private MongoDb(MongoClient client, String dbname) throws IllegalStateException {
        this.dbname = dbname;
        database = client.getDatabase(dbname);
    }

    public static MongoDb create(MongoClient client, String dbname) throws IllegalStateException {
        if (dbname == null)
            dbname = geometryDBname;

        if (inst == null) {
            Object obj = new Object();
            synchronized(obj) {
                if (inst == null)
                    inst = new MongoDb(client, dbname);
            }
        }

        return inst;
    }

    public static void clear() {
        inst.database = null;
        inst = null;
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        if (collectionName == null)
            collectionName = geometryCollectionName;

		MongoCollection<Document> collection = null;

		try {
			collection = database.getCollection(collectionName);
		} catch (IllegalStateException e) {
			collection = database.getCollection(geometryCollectionName);
			e.printStackTrace();
		}

		return collection;
	}
    
}
