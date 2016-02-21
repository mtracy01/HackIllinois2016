import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ji on 2/20/16.
 */
public class HelperFunctions {

    /*Delete a table*/
    public static void DeleteTable(Table table){
        try {
            System.out.println("Issuing DeleteTable request for " + table.getTableName());
            table.delete();
            System.out.println("Waiting for " + table.getTableName()
                    + " to be deleted...this may take a while...");
            table.waitForDelete();
        } catch (Exception e) {
            System.err.println("DeleteTable request failed for " + table.getTableName());
            System.err.println(e.getMessage());
        }
    }

    /*Create a new user with the given user id*/
    public static boolean createNewUser(String emailaddr,String userid, int iconid, Table table,AmazonDynamoDBClient client){
        if(true ) {  //check if the user id exists in the db
            Item item = new Item()
                    .withPrimaryKey("emailAddr", emailaddr)
                    .withString("userid", userid)
                    .withNumber("iconid", iconid);


            // Write the item to the table
            PutItemOutcome outcome = table.putItem(item);
        }
        return false;
    }


    /*Check if the item exists in a certain table*/
    private static boolean existItem(Table table, String newUserid,AmazonDynamoDBClient client) {
        ScanRequest scanRequest = new ScanRequest()
                .withTableName("Reply");

        ScanResult result = client.scan(scanRequest);
        for (Map<String, AttributeValue> item : result.getItems()) {
            // System.out.println(item.toJSON());
        }
       /* if(item == null ){
            System.out.println("uo");
            return false;
        }
        else return true;*/
        return true;
    }
}
