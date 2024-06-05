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
            addUser(splitLine[0]);
            Friendship f = new Friendship(splitLine[0], splitLine[1], splitLine[2]);
            addFriendship(f);
        }
    }

    public void addUser(String user) {
        if (!adj.containsKey(user)) {
            adj.put(user, new ArrayList<>());
            V++;
        }
    }

    public void addFriendship(Friendship f) {
        ArrayList<Friendship> list1 = adj.getOrDefault(f.getFriend1(), new ArrayList<>());
        list1.add(f);
        adj.put(f.getFriend1(), list1);

        if(!adj.containsKey(f.getFriend2())){
            addUser(f.getFriend2());
        }
        ArrayList<Friendship> list2 = adj.get(f.getFriend2());
        list1.add(new Friendship(f.getFriend2(), f.getFriend1(), f.getFriendship_strength()));
        adj.put(f.getFriend2(), list2);

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