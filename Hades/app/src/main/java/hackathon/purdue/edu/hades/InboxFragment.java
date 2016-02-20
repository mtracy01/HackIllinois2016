package hackathon.purdue.edu.hades;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hackathon.purdue.edu.hades.Adapters.EmailAdapter;

/**
 * Created by vieck on 2/20/16.
 */
public class InboxFragment extends Fragment {
    private RecyclerView mEmailView;
    private LinearLayoutManager mLayoutManager;
    private EmailAdapter mEmailAdapter;
    private List<String> mEmails;

    private FloatingActionButton mAddFAB;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        mEmailView = (RecyclerView) view.findViewById(R.id.email_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        fillEmails();
        mEmailAdapter = new EmailAdapter(getContext(),mEmails);

        mAddFAB = (FloatingActionButton) view.findViewById(R.id.btn_add);
        setupButton();
        return view;
    }

    private void fillEmails() {
        mEmails = new ArrayList<>();
        mEmails.add("Important! Reply Immediately");
        mEmails.add("Can I has a cookie?");
        mEmails.add("Lul catz");
        mEmails.add("Lower your health insurance now!");
        mEmails.add("Can you handle this diet pill?");
    }

    private void setupButton() {
        mAddFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Launch another fragment",Toast.LENGTH_LONG).show();
            }
        });
    }
}
