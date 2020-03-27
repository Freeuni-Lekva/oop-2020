import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This immutable data type represents a tweet from Twitter.
 */
public class Tweet {

    private String author;
    private String text;
    private Date timestamp;

    /**
     * Make a Tweet.
     * @param author    Twitter user who wrote the tweet.
     * @param text      text of the tweet
     * @param timestamp date/time when the tweet was sent
     */
    public Tweet(String author, String text, Date timestamp) {
        this.author = author;
        this.text = text;
        this.timestamp = new Date(timestamp.getTime());
    }

    /** @return Twitter user who wrote the tweet */
    public String getAuthor() {
        return author;
    }

    /** @return text of the tweet */
    public String getText() {
        return text;
    }

    /** @return date/time when the tweet was sent */
    public Date getTimestamp() {
        return new Date(timestamp.getTime());
    }

    /** @return a tweet that retweets t, one hour later*/
    public static Tweet retweetLater(Tweet t) {
        Date d = t.getTimestamp();
        d.setHours(d.getHours()+1);
        return new Tweet("rbmllr", t.getText(), d);
    }

    /** @return a list of 24 inspiring tweets, one per hour today */
    public static List<Tweet> tweetEveryHourToday () {
        List<Tweet> list = new ArrayList<Tweet>();
        Date date = new Date();
        for (int i=0; i < 24; i++) {
            date.setHours(i);
            list.add(new Tweet("rbmllr", "keep it up! you can do it", date));
        }
        return list;
    }
}