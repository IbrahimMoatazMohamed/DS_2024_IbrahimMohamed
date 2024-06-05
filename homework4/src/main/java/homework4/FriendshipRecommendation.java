package homework4;

public class FriendshipRecommendation implements Comparable<FriendshipRecommendation> {
    double recommendationStrength;
    String user;

    public FriendshipRecommendation(double recommendationStrength, String user) {
        this.recommendationStrength = recommendationStrength;
        this.user = user;
    }

    @Override
    public String toString() {
        return user + ": " + recommendationStrength;
    }

    public double getRecommendationStrength() {
        return recommendationStrength;
    }

    public String getUser() {
        return user;
    }

    @Override
    public int compareTo(FriendshipRecommendation o) {
        return Double.compare(recommendationStrength, o.getRecommendationStrength());
    }

    public void setRecommendationStrength(double recommendationStrength) {
        this.recommendationStrength = recommendationStrength;
    }
}
