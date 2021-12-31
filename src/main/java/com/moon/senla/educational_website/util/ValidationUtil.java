package com.moon.senla.educational_website.util;

import com.moon.senla.educational_website.error.IllegalRequestDataException;
import com.moon.senla.educational_website.model.User;

public class ValidationUtil {


    public static void checkNew(User user) {
        if (!user.isNew()) {
            throw new IllegalRequestDataException(user.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(User user, long id) {
        if (user.isNew()) {
            user.setId(id);
        } else if (user.getId() != id) {
            throw new IllegalRequestDataException(user.getClass().getSimpleName() + " must has id=" + id);
        }
    }
}
