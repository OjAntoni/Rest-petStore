package com.example.petstore.dao.InMemoryDao;

import com.example.petstore.dao.UserDao;
import com.example.petstore.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryUserDao implements UserDao {
    private static final List<User> users = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong index = new AtomicLong();

    @Override
    public void save(User user) {
        long currIndex = index.incrementAndGet();
        user.setId(currIndex);
        users.add(user);
    }

    @Override
    public void setStatus(long userId, int status) {
        User currUser;
        for (User user : users) {
            if(user.getId()==userId){
                currUser = user;
                currUser.setUserStatus(status);
                break;
            }
        }
    }

    @Override
    public void delete(long id) {
        users.removeIf(user -> user.getId()==id);
    }

    @Override
    public void update(long id, User newUser) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId()==id){
                users.set(i, newUser);
            }
        }
    }

    @Override
    public User getByUsername(String username) {
        User curr = null;
        for (User user : users) {
            if(user.getUsername().equals(username)){
                curr = user;
                break;
            }
        }
        return curr;
    }
}
