package homework4;

import java.util.*;

public class SocialNetwork {
    private int V;
    private int E;

    private HashMap<String, ArrayList<Friendship>> adj;

    public SocialNetwork() {
        this.V = 0;
        this.E = 0;
        adj = new HashMap<>();
    }

    public SocialNetwork(Scanner in) {
        this.V = 0;
        this.E = 0;
        adj = new HashMap<>();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("friend1;friend2;friendship_strength")) {
                continue;
            }
            String[] splitLine = line.split(";");

            String friend1 = splitLine[0];
            String friend2 = splitLine[1];
            double friendshipStrength = Double.parseDouble(splitLine[2]);

            addUser(friend1);
            addUser(friend2);
            addFriendship(new Friendship(friend1, friend2, friendshipStrength));
        }
    }

    public void addUser(String user) {
        if (!adj.containsKey(user)) {
            adj.put(user, new ArrayList<>());
            V++;
        }
    }

    public void addFriendship(Friendship f) {
        String friend1 = f.getFriend1();
        String friend2 = f.getFriend2();

        adj.get(friend1).add(f);
        adj.get(friend2).add(new Friendship(friend2, friend1, f.getFriendship_strength()));

        E++;
    }

    public ArrayList<FriendshipRecommendation> recommendFriends(String user) {
        if (!adj.containsKey(user)) return new ArrayList<>();

        ArrayList<String> blockNames = getUserList(adj.get(user));
        blockNames.add(user);

        HashMap<String, FriendshipRecommendation> preResult = new HashMap<>();
        for (Friendship fs1 : adj.get(user)) {
            for (Friendship fs2 : adj.get(fs1.getFriend2())) {
                String secondFriend = fs2.getFriend2();

                if (!blockNames.contains(secondFriend)) {
                    double weight = Double.min(fs1.getFriendship_strength(), fs2.getFriendship_strength());
                    FriendshipRecommendation recommendation;

                    if (preResult.containsKey(secondFriend)) {
                        recommendation = preResult.get(secondFriend);
                        recommendation.setRecommendationStrength(recommendation.getRecommendationStrength() + weight);
                    } else {
                        recommendation = new FriendshipRecommendation(weight, secondFriend);
                    }
                    preResult.put(secondFriend, recommendation);
                }
            }
        }

        ArrayList<FriendshipRecommendation> result = new ArrayList<>(preResult.values());
        Collections.sort(result);
        Collections.reverse(result);

        return result;
    }

    private ArrayList<String> getUserList(ArrayList<Friendship> friendships) {
        ArrayList<String> users = new ArrayList<>();
        for (Friendship friendship : friendships) {
            users.add(friendship.getFriend2());
        }
        return users;
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }
}