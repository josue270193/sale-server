package com.josue.saleserverddd.domain.events;

import com.josue.saleserverddd.domain.entities.User;

public interface UserNotification {

    boolean onCreated(User user);

    boolean onEdited(User user);

    boolean onDeleted(String id);

}
