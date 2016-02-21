package hackathon.purdue.edu.hades.mainMenu;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by Matthew on 2/20/2016.
 */
@DynamoDBTable(tableName = "Email_And_Picture")
public class iconInfo {
    private String email;
    private String project;

    @DynamoDBIndexHashKey(attributeName = "emailAddr")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = "project")
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}