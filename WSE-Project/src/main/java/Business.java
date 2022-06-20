/**
 * @author Kai
 * 12/23/18 3:28 AM
 */
public class Business {
    // {
    // "business_id":"Apn5Q_b6Nz61Tq4XzPdf9A",
    // "name":"Minhas Micro Brewery",
    // "neighborhood":"",
    // "address":"1314 44 Avenue NE",
    // "city":"Calgary",
    // "state":"AB",
    // "postal_code":"T2E 6L6",
    // "latitude":51.0918130155,
    // "longitude":-114.031674872,
    // "stars":4.0,
    // "review_count":24,
    // "is_open":1,
    // "attributes":{"BikeParking":"False", "BusinessAcceptsCreditCards":"True", "BusinessParking":"{'garage': False, 'street': True, 'validated': False, 'lot': False, 'valet': False}", "GoodForKids":"True", "HasTV":"True", "NoiseLevel":"average", "OutdoorSeating":"False", "RestaurantsAttire":"casual", "RestaurantsDelivery":"False", "RestaurantsGoodForGroups":"True", "RestaurantsPriceRange2":"2", "RestaurantsReservations":"True", "RestaurantsTakeOut":"True"},
    // "categories":"Tours, Breweries, Pizza, Restaurants, Food, Hotels & Travel",
    // "hours":{"Monday":"8:30-17:0", "Tuesday":"11:0-21:0", "Wednesday":"11:0-21:0", "Thursday":"11:0-21:0", "Friday":"11:0-21:0", "Saturday":"11:0-21:0"}
    // }
    private final String business_id;
//    private final String name;
//    private final String city;
//    private final String state;
    private final Double stars;
    private final Integer review_count;
    
    private Business() {
        this(null, null, null);
    }


    public Business(String business_id, Double stars, Integer review_count) {
        this.business_id = business_id;
        this.stars = stars;
        this.review_count = review_count;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public Double getStars() {
        return stars;
    }

    public Integer getReview_count() {
        return review_count;
    }

    private static final String[] KEYS = { "business_id", "stars", "review_count"};

    public static String getHeader() {
        return String.join(",", KEYS);
    }

    public String getItem() {
        return String.join(",", new String[] {
                getBusiness_id(),
                String.valueOf(getStars()),
                String.valueOf(getReview_count())
        });
    }
}
