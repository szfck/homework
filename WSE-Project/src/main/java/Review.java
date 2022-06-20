/**
 * @author Kai
 * 12/23/18 2:35 AM
 */
public class Review {
//  {
//  "review_id":"x7mDIiDB3jEiPGPHOmDzyw",
//  "user_id":"msQe1u7Z_XuqjGoqhB0J5g",
//  "business_id":"iCQpiavjjPzJ5_3gPD5Ebg",
//  "stars":2,
//  "date":"2011-02-25",
//  "text":"The pizza was okay. Not the best I've had. I prefer Biaggio's on Flamingo \/ Fort Apache. The chef there can make a MUCH better NY style pizza. The pizzeria @ Cosmo was over priced for the quality and lack of personality in the food. Biaggio's is a much better pick if youre going for italian - family owned, * home made recipes, people that actually CARE if you like their food. You dont get that at a pizzeria in a casino. I dont care what you say...", *
//  "useful":0,
//  "funny":0,
//  "cool":0
//  }
    private final String review_id;
    private final String user_id;
    private final String business_id;
    private final Integer stars;
    private final String date;
    private final String text;
    private final Integer useful;
    private final Integer funny;
    private final Integer cool;

    private Review() {
        this(null, null, null, null, null, null, null, null, null);
    }

    public Review(String review_id, String user_id, String business_id, Integer stars, String date, String text, Integer useful, Integer funny, Integer cool) {
        this.review_id = review_id;
        this.user_id = user_id;
        this.business_id = business_id;
        this.stars = stars;
        this.date = date;
        this.text = text;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
    }

    public String getReview_id() { return review_id; }

    public String getUser_id() { return user_id; }

    public String getBusiness_id() { return business_id; }

    public Integer getStars() { return stars; }

    public String getDate() { return date; }

    public String getText() {
        return "";
//        return text;
    }

    public Integer getUseful() { return useful; }

    public Integer getFunny() { return funny; }

    public Integer getCool() { return cool; }

    private static final String[] KEYS = { "review_id", "user_id", "business_id", "stars", "date", "text", "useful", "funny", "cool"};

    public static String getHeader() {
        return String.join(",", KEYS);
    }

    public String getItem() {
        return String.join(",", new String[] {
                getReview_id(),
                getUser_id(),
                getBusiness_id(),
                String.valueOf(getStars()),
                getDate(),
                getText(),
                String.valueOf(getUseful()),
                String.valueOf(getFunny()),
                String.valueOf(getCool())
        });
    }
}
