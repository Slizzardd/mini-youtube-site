package ua.com.alevel.persistence.entity.comment;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.entity.video.Video;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private String name;

    private String text;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @ManyToMany(mappedBy = "likesComments")
    private Set<Channel> whoIsLikes;


    public Comment() {
        super();
        this.whoIsLikes = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Channel> getWhoIsLikes() {
        return whoIsLikes;
    }

    public void setWhoIsLikes(Set<Channel> whoIsLikes) {
        this.whoIsLikes = whoIsLikes;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(name, comment.name) && Objects.equals(text, comment.text) && Objects.equals(channel, comment.channel) && Objects.equals(whoIsLikes, comment.whoIsLikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text, channel, whoIsLikes);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", channel=" + channel +
                '}';
    }
}
