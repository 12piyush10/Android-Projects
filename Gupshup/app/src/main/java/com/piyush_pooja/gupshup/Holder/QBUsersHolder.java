package com.piyush_pooja.gupshup.Holder;

import android.util.SparseArray;

import com.quickblox.users.model.QBUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyush-Pooja on 4/29/2017.
 */

public class QBUsersHolder {

    public static  QBUsersHolder instance;

    private SparseArray<QBUser> qbUserSparseArray;

    public static synchronized QBUsersHolder getInstance(){
        if (instance == null)
            instance = new QBUsersHolder();
        return instance;

    }

    private QBUsersHolder()
    {
        qbUserSparseArray = new SparseArray<>();
    }
    public void putUsers(List<QBUser> users)
    {
        for (QBUser user:users)
            putUser(user);
    }

    private void putUser(QBUser user) {

        qbUserSparseArray.put(user.getId(),user);
    }

    public QBUser getUserById(int id)
    {
        return qbUserSparseArray.get(id);
    }

    public List<QBUser> getUserByIds(List<Integer> ids){
        List<QBUser> qbUser = new ArrayList<>();
        for (Integer id:ids){
            QBUser user = getUserById(id);
            if (user != null)
                qbUser.add(user);
        }
        return qbUser;

    }
}
