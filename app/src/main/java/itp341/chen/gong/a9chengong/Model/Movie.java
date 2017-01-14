package itp341.chen.gong.a9chengong.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gongchen on 11/7/16.
 */
public class Movie  implements Serializable {
    //Instance variables
    private String title;
    private String description;
    private int genre;
    private ArrayList<String> commentArrayList;

    public Movie(){
        super();
    }

    public Movie(String titleInput, String descriptionInput, int gengreInput
            ){
        super();
        this.title = titleInput;
        this.description = descriptionInput;
        this.genre = gengreInput;
        this.commentArrayList = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genre +
                ", commentList='" + commentArrayList + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public ArrayList<String> getCommentList() {
        return commentArrayList;
    }

    public void setCommentList(ArrayList<String> commentList) {
        this.commentArrayList = commentList;
    }
}
