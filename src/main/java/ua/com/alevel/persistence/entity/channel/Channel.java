package ua.com.alevel.persistence.entity.channel;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.comment.Comment;
import ua.com.alevel.persistence.entity.video.Video;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("CHANNEL")
public class Channel extends BaseEntity {

    @OneToOne(mappedBy = "channel")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String pathToAvatar;

    @Column(nullable = false, unique = true)
    private String login;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
    })
    @JoinTable(name = "subscribers",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "whoIsSubscribed_id")
    )
    private Set<User> subscribers;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Video> videos;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
    })
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<Video> likes;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
    })
    @JoinTable(name = "likes_comments",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "comments_id")
    )
    private Set<Comment> likesComments;

    public Channel() {
        super();
        // @TODO Insert a link to a basic avatar
        this.pathToAvatar = "";
        this.subscribers = new HashSet<>();
        this.comments = new HashSet<>();
        this.videos = new HashSet<>();
        this.likes = new HashSet<>();
        this.likesComments = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathToAvatar() {
        return pathToAvatar;
    }

    public void setPathToAvatar(String pathToAvatar) {
        this.pathToAvatar = pathToAvatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }

    public Set<Video> getLikes() {
        return likes;
    }

    public void setLikes(Set<Video> likes) {
        this.likes = likes;
    }

    public Set<Comment> getLikesComments() {
        return likesComments;
    }

    public void setLikesComments(Set<Comment> likesComments) {
        this.likesComments = likesComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(subscribers, channel.subscribers) && Objects.equals(videos, channel.videos) && Objects.equals(likes, channel.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscribers, videos, likes);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "subscribers=" + subscribers +
                '}';
    }
}
