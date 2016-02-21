package hackathon.purdue.edu.hades.mainMenu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import com.amazonaws.services.dynamodbv2.model.*;

import com.facebook.FacebookActivity;

import hackathon.purdue.edu.hades.Adapters.ArrayAdapterWithIcon;
import hackathon.purdue.edu.hades.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link createProjectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link createProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createProjectFragment extends Fragment {

    private EditText projectName;
    private ImageView projectIconView;
    private EditText projectEmail;


    private OnFragmentInteractionListener mListener;

    public createProjectFragment() {
        // Required empty public constructor
    }


    public static createProjectFragment newInstance() {
        createProjectFragment fragment = new createProjectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_project, container, false);
        projectName = (EditText)v.findViewById(R.id.project_name_edittext);
        projectEmail = (EditText)v.findViewById(R.id.email_name_edittext);
        projectIconView = (ImageView) v.findViewById(R.id.imageSelect);
        projectIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] names = new String[] {"Android","HTML5","Facebook","Rocket","Book"};
                final Integer[] icons = new Integer[] {R.mipmap.ic_action_android,
                R.mipmap.ic_action_html5,R.mipmap.ic_action_facebook,R.mipmap.ic_action_rocket,
                R.mipmap.ic_action_book};
                final ListAdapter adapter = new ArrayAdapterWithIcon(getContext(),names,icons);
                new AlertDialog.Builder(getActivity()).setTitle("Select an Image")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                projectIconView.setBackground(getActivity().getDrawable(icons[which]));
                            }
                        }).show();
            }
        });
        Button b = (Button)v.findViewById(R.id.submit_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Check that all parameters are valid, if they are, tell the server to create the email, else give error message to user
                String pName = projectName.getText().toString();
                String pEmail = projectEmail.getText().toString();
                int image = 0;
                 Drawable android = getActivity().getDrawable(R.mipmap.ic_action_android);
                Drawable html5 = getActivity().getDrawable(R.mipmap.ic_action_html5);
                Drawable facebook = getActivity().getDrawable(R.mipmap.ic_action_facebook);
                Drawable rocket = getActivity().getDrawable(R.mipmap.ic_action_rocket);
                Drawable book = getActivity().getDrawable(R.mipmap.ic_action_book);
                Drawable sImage = projectIconView.getBackground();
                if(sImage==android)
                    image=1;
                else if(sImage==html5)
                    image=2;
                else if(sImage==facebook)
                    image=3;
                else if(sImage==rocket)
                    image=4;
                else if(sImage==book)
                    image=5;
                CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                        getActivity().getApplicationContext(),
                        "us-east-1:8258ab02-1aea-49f2-b6cf-a6159cc2c8d0",
                        Regions.DEFAULT_REGION
                );
                AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient(credentialsProvider);


            }
        });

        return v;
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
