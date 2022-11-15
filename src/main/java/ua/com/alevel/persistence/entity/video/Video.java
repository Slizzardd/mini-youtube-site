package ua.com.alevel.persistence.entity.video;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.entity.comment.Comment;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "video")
public class Video extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String tags;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Channel channel;

    @ManyToMany(mappedBy = "likes")
    private Set<Channel> whoIsLikes;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments;

    public Video() {
        super();
        this.whoIsLikes = new HashSet<>();
        this.comments = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<Channel> getWhoIsLikes() {
        return whoIsLikes;
    }

    public void setWhoIsLikes(Set<Channel> whoIsLikes) {
        this.whoIsLikes = whoIsLikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(title, video.title) && Objects.equals(tags, video.tags) && Objects.equals(description, video.description) && Objects.equals(channel, video.channel) && Objects.equals(whoIsLikes, video.whoIsLikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, tags, description, channel, whoIsLikes);
    }

    @Override
    public String toString() {
        return "Video{" +
                "title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", description='" + description + '\'' +
                ", channel=" + channel +
                ", whoIsLikes=" + whoIsLikes +
                '}';
    }
}
