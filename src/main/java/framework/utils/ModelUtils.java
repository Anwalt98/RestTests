package framework.utils;

import framework.models.Post;
import framework.models.userModels.User;

import java.util.List;

public class ModelUtils {
    public static boolean areRightSorted(List<Post> data){
        for (int i=0; i< data.size()-1; i++){
            if (data.get(i).getId() > data.get(i+1).getId()){
                return false;
            }
        }
        return true;
    }

    public static Post getRandomPost(int userId){
        return new Post(userId, RandomUtils.getRandomInt(),
                RandomUtils.getRandomString(Constants.RANDOM_STRING_SIZE),
                RandomUtils.getRandomString(Constants.RANDOM_STRING_SIZE));
    }

    public static User getById(List<User> listOfUsers, int id){
        for (User user : listOfUsers){
            if (user.getId() == id) {
                return user;
            }
        }
    return null;
    }
}
