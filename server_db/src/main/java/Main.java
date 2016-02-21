// Copyright 2012-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// Licensed under the Apache License, Version 2.0.



import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
                .withEndpoint("http://localhost:8000");//dynamodb.us-west-2.amazonaws.com

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "UserID_And_Email";

        try {
            HelperFunctions.DeleteTable(dynamoDB.getTable(tableName));
            System.out.println("Attempting to create table; please wait...");
            ArrayList<AttributeDefinition> attributeDefinitions= new ArrayList<AttributeDefinition>();
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

            Table table = dynamoDB.createTable(request);
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
            if(HelperFunctions.createNewUser("apple","ji",1,table)) System.out.println("Hayoo!");

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }

    }

}