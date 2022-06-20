/**
 * @author Kai
 * 12/23/18 12:01 PM
 */
public class User {
    // {"user_id":"QPT4Ud4H5sJVr68yXhoWFw","name":"Andy","review_count":1,
    // "yelping_since":"2016-07-21","friends":"None","useful":0,"funny":0,
    // "cool":0,"fans":0,"elite":"None","average_stars":4.0,"compliment_hot":0,
    // "compliment_more":0,"compliment_profile":0,"compliment_cute":0,
    // "compliment_list":0,"compliment_note":0,"compliment_plain":0,
    // "compliment_cool":0,"compliment_funny":0,"compliment_writer":0,"compliment_photos":0}
    private final String user_id;
    private final Integer review_count;
    private final String yelping_since;
    private final Double average_stars;

    private User() {
        this(null, null, null, null);
    }

    public User(String user_id, Integer review_count, String yelping_since, Double average_stars) {
        this.user_id = user_id;
        this.review_count = review_count;
        this.yelping_since = yelping_since;
        this.average_stars = average_stars;
    }

    public String getUser_id() {
        return user_id;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public String getYelping_since() {
        return yelping_since;
    }

    public Double getAverage_stars() {
        return average_stars;
    }

    private static final String[] KEYS = { "user_id", "review_count", "yelping_since", "average_stars" };

    public static String getHeader() {
        return String.join(",", KEYS);
    }

    public String getItem() {
        return String.join(",", new String[] {
                getUser_id(),
                String.valueOf(getReview_count()),
                getYelping_since(),
                String.valueOf(getAverage_stars())
        });
    }
}
