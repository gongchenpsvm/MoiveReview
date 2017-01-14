package itp341.chen.gong.a9chengong;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

import itp341.chen.gong.a9chengong.Model.Movie;
import itp341.chen.gong.a9chengong.Model.MovieSingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainListFragment extends Fragment {

    private static final String TAG = MainListFragment.class.getSimpleName();

    Button mButtonAdd;
    ListView mListView;
    private ArrayList<Movie> mMovies;
    private MovieAdapter mAdapter;
    public MainListFragment() {
        // Required empty public constructor
    }

    public static MainListFragment newInstance() {
        Bundle args = new Bundle();

        MainListFragment f = new MainListFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0){
            if (resultCode == Activity.RESULT_OK){
                Log.d(TAG, "Return from CreateFragment and update adapter");
                mAdapter.notifyDataSetChanged();//Update adapter when come from child activity
                //SetResult works at CreateFragment by calling getActivity() // TODO: 11/7/16
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_list, container, false);

        //find views
        mButtonAdd = (Button) v.findViewById(R.id.buttonAdd);
        mListView = (ListView)v.findViewById(R.id.listView);
        //Create and store adapter
        mMovies = MovieSingleton.get(getActivity()).getMovies();
        mAdapter = new MovieAdapter(mMovies);
        //Assign adapter to list
        mListView.setAdapter(mAdapter);
        //Click ADD
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateActivity.class);
                startActivityForResult(intent,0);//Unable to call startActivity?
            }
        });

        return v;
    }

    public class MovieAdapter extends ArrayAdapter<Movie> {
        //Arrayadapter build on arraylist for data
        ArrayList<Movie> mMovies;
        //Overload constructor. Usually similar
        public MovieAdapter (ArrayList<Movie> moviesInput){
            super(getActivity(),0, moviesInput);//No layout yet
            //Store
            mMovies = moviesInput;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Check if row has been created
            //convertView represents a single row layout
            if(convertView == null) {//See if row has never been created
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.layout_list_movie,null);
            }
            //Load data from model to a single row
            //Access current row of model data
            Movie m = mMovies.get(position);
            //Reference to widgets
            Button buttonMore = (Button)convertView.findViewById(R.id.buttonMore);
            TextView textTile = (TextView)convertView.findViewById(R.id.list_movie_title);
            TextView textDescription = (TextView)convertView.findViewById(R.id.list_movie_description);
            ImageView imageGenre = (ImageView)convertView.findViewById(R.id.list_movie_image);
            //Inflate information
            buttonMore.setTag(Integer.valueOf(position));
            textTile.setText(m.getTitle());
            textDescription.setText(m.getDescription());//Load done
            int genre = m.getGenre();//0:Horror 1:Action 2:Drama 3:Comedy 4:Sci-Fi
            switch (genre) {
                case -1:
                    imageGenre.setImageResource(R.drawable.empty);
                    break;
                case 0:
                    imageGenre.setImageResource(R.drawable.horror);
                    break;
                case 1:
                    imageGenre.setImageResource(R.drawable.action);
                    break;
                case 2:
                    imageGenre.setImageResource(R.drawable.drama);
                    break;
                case 3:
                    imageGenre.setImageResource(R.drawable.comedy);
                    break;
                case 4:
                    imageGenre.setImageResource(R.drawable.scifi);
                    break;
            }
            buttonMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "mListView: onListItemClick");
                    //TODO
                    int viewPosition = (Integer)view.getTag();
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra(DetailActivity.VIEW_POSITION, viewPosition);
                    startActivity(intent);
                }
            });

            //TODO
            return convertView;
        }
    }
}
