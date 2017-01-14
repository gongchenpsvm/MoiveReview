package itp341.chen.gong.a9chengong;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import itp341.chen.gong.a9chengong.Model.Movie;
import itp341.chen.gong.a9chengong.Model.MovieSingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {

    private EditText editTextAddTitle;
    private EditText editTextAddDescription;
    private Spinner spinnerAddGenre;
    private Button buttonSave;
    private ImageView imageShowGenre;

    int genreSelected;
    public CreateFragment() {
        // Required empty public constructor
    }

    public static CreateFragment newInstance() {
        Bundle args = new Bundle();

        CreateFragment f = new CreateFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create, container, false);

        //References to widgets
        editTextAddTitle = (EditText) v.findViewById(R.id.editTextAddTitle);
        editTextAddDescription = (EditText)v.findViewById(R.id.editTextAddDescription);
        spinnerAddGenre = (Spinner)v.findViewById(R.id.spinnerAddGenre);
        imageShowGenre = (ImageView)v.findViewById(R.id.imageViewShowGenre);
        buttonSave = (Button)v.findViewById(R.id.buttonSave);
        //Inflate spinner with adapter
        //final String [] genres = getResources().getStringArray(R.array.genres);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSpinnerAddGenre = ArrayAdapter.createFromResource(getActivity(),
                R.array.genres, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSpinnerAddGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerAddGenre.setAdapter(adapterSpinnerAddGenre);

        //Change image with the genre selected
        spinnerAddGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genreSelected = i;
                //Change image based on the genre selected
                switch (genreSelected){
                    case 0:
                        imageShowGenre.setImageResource(R.drawable.horror);
                        break;
                    case 1:
                        imageShowGenre.setImageResource(R.drawable.action);
                        break;
                    case 2:
                        imageShowGenre.setImageResource(R.drawable.drama);
                        break;
                    case 3:
                        imageShowGenre.setImageResource(R.drawable.comedy);
                        break;
                    case 4:
                        imageShowGenre.setImageResource(R.drawable.scifi);
                        break;
                    case -1:
                        imageShowGenre.setImageResource(R.drawable.empty);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                genreSelected = -1;
                imageShowGenre.setImageResource(R.drawable.empty);

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Collect info
                String title = editTextAddTitle.getText().toString();
                String description = editTextAddDescription.getText().toString();
                //Construct a movie
                Movie m = new Movie(title,description,genreSelected);
                //Add a movie
                MovieSingleton.get(getActivity()).addMovie(m);
                //Return to the parent activity
                getActivity().setResult(Activity.RESULT_OK, new Intent());
                getActivity().finish();

            }
        });
        return v;
    }

}
