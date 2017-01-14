package itp341.chen.gong.a9chengong;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import itp341.chen.gong.a9chengong.Model.Movie;
import itp341.chen.gong.a9chengong.Model.MovieSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = DetailFragment.class.getSimpleName();
    public static final String VIEW_POSITION = "VIEW_POSITION";
    private String commentAdded;
    private Button buttonAddComment;
    private EditText editTextMoreMovieAddComment;
    private ListView listViewMoreMovieComments;
    private TextView textViewMoreMovieDescription;
    private TextView textViewMoreMovieTitle;
    private ImageView imageViewMovieGenre;

    public DetailFragment() {
        // Required empty public constructor
    }
    public static DetailFragment newInstance(int viewPosition) {
        Bundle args = new Bundle();
        args.putInt(VIEW_POSITION,viewPosition);
        DetailFragment f = new DetailFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        buttonAddComment = (Button)v.findViewById(R.id.buttonAddComment);
        editTextMoreMovieAddComment = (EditText)v.findViewById(R.id.editTextMoreMovieAddComment);
        listViewMoreMovieComments = (ListView)v.findViewById(R.id.listViewMoreMovieComments);
        textViewMoreMovieDescription = (TextView)v.findViewById(R.id.textViewMoreMovieDescription);
        textViewMoreMovieTitle = (TextView)v.findViewById(R.id.textViewMoreMovieTitle);
        imageViewMovieGenre = (ImageView)v.findViewById(R.id.imageViewMovieGenre);

        //Get the clicked view's position
        int viewPosition = getArguments().getInt(VIEW_POSITION);
        Log.d(TAG, "User clicks "+Integer.toString(viewPosition)
                + " MORE button, call DetailActivity, and call DetailFragment.");
        //As well as the corresponding movie object
        final Movie m = MovieSingleton.get(getActivity()).getMovieAtIndex(viewPosition);
        //Display specific movie information
            //Display Text
        textViewMoreMovieTitle.setText(m.getTitle());
        textViewMoreMovieDescription.setText(m.getDescription());
            //Image
        switch (m.getGenre()){
            case -1:
                imageViewMovieGenre.setImageResource(R.drawable.empty);
                break;
            case 0:
                imageViewMovieGenre.setImageResource(R.drawable.horror);
                break;
            case 1:
                imageViewMovieGenre.setImageResource(R.drawable.action);
                break;
            case 2:
                imageViewMovieGenre.setImageResource(R.drawable.drama);
                break;
            case 3:
                imageViewMovieGenre.setImageResource(R.drawable.comedy);
                break;
            case 4:
                imageViewMovieGenre.setImageResource(R.drawable.scifi);
                break;
        }
            //Display Comments
        final ArrayAdapter<String> commentAdapter
                = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                m.getCommentList());
        listViewMoreMovieComments.setAdapter(commentAdapter);

        //Retrieve comment typed
        editTextMoreMovieAddComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                commentAdded = editTextMoreMovieAddComment.getText().toString();
                return false;
            }
        });

        buttonAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activeComment = editTextMoreMovieAddComment.getText().toString();
                Log.d(TAG, activeComment);
                if (!activeComment.equals(commentAdded)){
                    m.getCommentList().add(activeComment);
                }
                else {
                    m.getCommentList().add(commentAdded);
                }
                editTextMoreMovieAddComment.getText().clear();
                commentAdded = "";
                commentAdapter.notifyDataSetChanged();
            }
        });

        return v;
    }

}
