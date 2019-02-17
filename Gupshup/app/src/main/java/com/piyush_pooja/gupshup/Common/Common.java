package com.piyush_pooja.gupshup.Common;

import com.piyush_pooja.gupshup.Holder.QBUsersHolder;
import com.quickblox.users.model.QBUser;

import java.util.List;

/**
 * Created by Piyush-Pooja on 4/29/2017.
 */

public class Common {

    public static  String createChatDialogName(List<Integer> qbUsers){

        List<QBUser>  qbUsers1 = QBUsersHolder.getInstance().getUserByIds(qbUsers);
        StringBuilder name = new StringBuilder();

        for (QBUser user:qbUsers1)
            name.append(user.getFullName()).append(" ");
        if (name.length() > 30)
            name = name.replace(30,name.length()-1,"...");
        return name.toString();
    }
}
