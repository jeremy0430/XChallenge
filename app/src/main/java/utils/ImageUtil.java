package utils;

import android.widget.ImageView;

import java.lang.reflect.Field;

public class ImageUtil {
    public static int getV7ImageResourceId(ImageView imageView) {
        int imgid = 0;
        Field[] fields=imageView.getClass().getDeclaredFields();
        for(Field f:fields){
            if(f.getName().equals("mBackgroundTintHelper")){
                f.setAccessible(true);
                try {
                    Object obj = f.get(imageView);
                    Field[] fields2=obj.getClass().getDeclaredFields();
                    for(Field f2:fields2){
                        if(f2.getName().equals("mBackgroundResId")){
                            f2.setAccessible(true);
                            imgid = f2.getInt(obj);
                            android.util.Log.d("1111","Image ResourceId:"+imgid);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return imgid;
    }
}
