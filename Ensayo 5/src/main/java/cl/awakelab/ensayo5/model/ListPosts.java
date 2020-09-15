package cl.awakelab.ensayo5.model;

public class ListPosts {

    private Posts post;
    private Users user;
    private int n_comments;

    public ListPosts() {

    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getN_comments() {
        return n_comments;
    }

    public void setN_comments(int n_comments) {
        this.n_comments = n_comments;
    }

}
