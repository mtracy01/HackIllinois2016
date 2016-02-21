package hackathon.purdue.edu.hades.mainMenu;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by Matthew on 2/20/2016.
 */
@DynamoDBTable(tableName = "UserID_And_Email")
public class userInfo {
    private String userID = "testDD";
    private String email = "testDD";

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserID() {
        return userID;
    }

    public void setUserId(String userID) {
        this.userID = userID;
    }

    @DynamoDBAttribute(attributeName = "emailAddr")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}