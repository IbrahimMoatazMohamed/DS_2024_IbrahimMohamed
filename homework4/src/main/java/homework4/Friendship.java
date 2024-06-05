package homework4;

public class Friendship {
    private final String friend1;
    private final String friend2;
    private final double friendship_strength;


    public Friendship(String friend1, String friend2, String friendship_strength) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.friendship_strength = Double.parseDouble(friendship_strength);
    }

    public Friendship(String friend1, String friend2, double friendship_strength) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.friendship_strength = friendship_strength;
    }

    public String getFriend1() {
        return friend1;
    }

    public String getFriend2() {
        return friend2;
    }

    public double getFriendship_strength() {
        return friendship_strength;
    }
}
