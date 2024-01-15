package com.momoc.multi.chat.room.common.utils;

import java.util.Collection;
import java.util.Map;
/**
 * @author momoc
 */

public class AssertUtil {

    public static Boolean isEmpty(Object obj)  {
        if (obj == null){
            return true;
        }
        if (obj instanceof String){
            return  ((String) obj).isEmpty();
        }
        if (obj instanceof Collection){
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map){
            return ((Map)obj).isEmpty();
        }
        return false;
    }

//    public static Boolean isEmpty(Object ...objs)  {
//        for (Object obj : objs) {
//          if(!isEmpty(obj)){
//              return false;
//          }
//        }
//        return true;
//    }

}
