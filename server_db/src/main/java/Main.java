

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("dynamodb.us-west-2.amazonaws.com");

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "UserID_And_Email";
        Table table;
        try {

            //HelperFunctions.DeleteTable(dynamoDB.getTable(tableName));
            if(dynamoDB.getTable(tableName)== null) {
                System.out.println("Attempting to create table; please wait...");

                ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
                attributeDefinitions.add(new AttributeDefinition().withAttributeName("emailAddr").withAttributeType("S"));

                ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
                keySchema.add(new KeySchemaElement().withAttributeName("emailAddr").withKeyType(KeyType.HASH));

                CreateTableRequest request = new CreateTableRequest()
                        .withTableName(tableName)
                        .withKeySchema(keySchema)
                        .withAttributeDefinitions(attributeDefinitions)
                        .withProvisionedThroughput(new ProvisionedThroughput()
                                .withReadCapacityUnits(10L)
                                .withWriteCapacityUnits(10L));

                table = dynamoDB.createTable(request);
                table.waitForActive();
                System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
            }
            else {
                table = dynamoDB.getTable(tableName);
                if(HelperFunctions.createNewUser("apaple","haha",1,table,client)) System.out.println("Hayoo!");
            }
            if(HelperFunctions.createNewUser("apple","ji",1,table,client)) System.out.println("Hayoo!");

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }

    }

}