package sample;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class JavaMongoConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase dbs;
    private static MongoCollection<Document> coll;

    public static void createConnection()
    {

        mongoClient=new MongoClient("localhost",27017);
        dbs=mongoClient.getDatabase("PRS_db"); // ProjectRecommenderSystem_DB
        coll=dbs.getCollection("Users");

    }

    public static boolean addUserDB(sample.UserProfile user)
    {
        user.setName(user.getName().trim()); // TO REMOVE SPACES AFTER THE NAME

        Document query =new Document("Username",user.getName());  // CHECKING IF USERNAME ALREADY EXISTS
        Document doc=coll.find(query).first();

        if(doc==null) {
            //ADD THE USER IF USERNAME NOT FOUND
            Document doc1=new Document("Username",user.getName());
            doc1.append("Data",new Document("Name",user.getName()).append("Password",user.getPassword()).append("Location",user.getLocation())
                    .append("Contact",user.getContact()).append("Interests",user.getInterests()));
            coll.insertOne(doc1);
            return true;
        }
        else {
         return false;
        }

    }
    public static boolean loginUser(String uname, String pass) //AUTHORISE USER
    {

        Document query =new Document("Username",uname);

        Document doc=coll.find(query).first();

        if(doc==null)
        {
            return false;
        }
        Document doc1=(Document)doc.get("Data");

        if(doc1.get("Password").equals(pass))
        {
            return true;
        }
        else
        {
            return false;
        }



    }



}
