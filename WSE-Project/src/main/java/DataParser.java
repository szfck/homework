import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kai
 * 12/23/18 12:17 AM
 */
public class DataParser {

    private static void genReviewCsv(String inputPath, String outputPath) throws IOException {

        File file = new File(inputPath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        file = new File(outputPath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write(Review.getHeader() + '\n');

        String line = null;
        ObjectMapper mapper = new ObjectMapper();

        while ((line = br.readLine()) != null) {
            Review review = mapper.readValue(line, Review.class);
            bw.write(review.getItem() + '\n');
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void genBusinessCsv(String inputPath, String outputPath) throws IOException {

        File file = new File(inputPath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        file = new File(outputPath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write(Business.getHeader() + '\n');

        String line = null;
        ObjectMapper mapper = new ObjectMapper();

        // ignore unknown attributes
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        while ((line = br.readLine()) != null) {
            Business business = mapper.readValue(line, Business.class);
            bw.write(business.getItem() + '\n');
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void genUserCsv(String inputPath, String outputPath) throws IOException {

        File file = new File(inputPath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        file = new File(outputPath);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write(User.getHeader() + '\n');

        String line = null;
        ObjectMapper mapper = new ObjectMapper();

        // ignore unknown attributes
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        while ((line = br.readLine()) != null) {
            User user = mapper.readValue(line, User.class);
            bw.write(user.getItem() + '\n');
        }
        bw.flush();
        br.close();
        bw.close();
    }
    public static void main(String[] args) throws IOException {
        genReviewCsv("./src/main/resources/yelp_academic_dataset_review.json", "./src/main/resources/yelp_academic_dataset_review.csv");
        genBusinessCsv("./src/main/resources/yelp_academic_dataset_business.json", "./src/main/resources/yelp_academic_dataset_business.csv");
        genUserCsv("./src/main/resources/yelp_academic_dataset_user.json", "./src/main/resources/yelp_academic_dataset_user.csv");

    }


}
