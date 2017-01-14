package itp341.chen.gong.a9chengong.Model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by gongchen on 11/7/16.
 */
public class MovieSingleton {
    //Instance variables
    private ArrayList<Movie> mMovies;
    private Context mContext;   //android-specific so we can access resources
    private static MovieSingleton mSingleton;  //static means this exists APART from any specific instance

    //Create a singleton if not created yet
    private MovieSingleton(Context contextInput){
        mContext = contextInput;
        mMovies = new ArrayList<Movie>();//Necessary to specify the type???
        //Add dummy data to start
            Movie m = new Movie("MovieTitle Test ",
                    "MovieDescription Test ",
                    -1);
            m.getCommentList().add("Test Comment0, Test Comment0, Test Comment0, Test Comment0");
            m.getCommentList().add("Test Comment1, Test Comment1");
            m.getCommentList().add("Test Comment2, Test Comment2");
            m.getCommentList().add("Test Comment3, Test Comment3");
            m.getCommentList().add("Test Comment4, Test Comment4");
            m.getCommentList().add("Test Comment5, Test Comment5");
            m.getCommentList().add("Test Comment6, Test Comment6");
            mMovies.add(m);
    }

    public ArrayList<Movie> getMovies(){return mMovies;}

    //Get singleton
    public static MovieSingleton get(Context contextInput){
        if (mSingleton == null) {
            mSingleton = new MovieSingleton(contextInput);
        }
        return mSingleton;
    }

    //Get number of movie objects
    public int getNumMovies(){
        if (mSingleton == null){
            return -1;
        }
        else {
            return mMovies.size();
        }
    }

    //Get a Movie at specific index
    public Movie getMovieAtIndex (int index){
        return mMovies.get(index);
    }

    //Add a Movie object
    public void addMovie (Movie movieInput){
        mMovies.add(movieInput);
    }

    //Add comment to specific movie
    public void addCommentToMovieAtIndex(String commentInput, int index){
        //TODO
        mMovies.get(index).setDescription(commentInput);//Return copy or reference???
    }
}
