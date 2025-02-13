import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighbor.UserSimilarityImpl;
import org.apache.mahout.cf.taste.impl.neighbor.UserNeighborhoodImpl;
import org.apache.mahout.cf.taste.impl.recommendation.RecommendedItem;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;

import java.io.File;
import java.util.List;

public class ProductRecommendation {

    public static void main(String[] args) throws Exception {

        // Load the dataset (CSV format) into a DataModel object
        File file = new File("data/user_ratings.csv");
        DataModel model = new FileDataModel(file);

        // Compute user similarity (you can change the similarity measure as needed)
        UserSimilarity similarity = new UserSimilarityImpl(model);

        // Set the neighborhood (i.e., similar users) to consider
        UserNeighborhood neighborhood = new UserNeighborhoodImpl(2, similarity, model);

        // Create a recommender based on the model, similarity, and neighborhood
        Recommender recommender = new Recommender(model, similarity, neighborhood);

        // Get recommendations for user 1 (ID = 1) for example
        List<RecommendedItem> recommendations = recommender.recommend(1, 3); // Recommend 3 items

        // Display the recommendations
        System.out.println("Recommendations for User 1:");
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("Item ID: " + recommendation.getItemID() + ", Value: " + recommendation.getValue());
        }
    }
}
