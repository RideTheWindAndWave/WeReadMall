package com.weteam.java.design.entity.gifts_factory;

import com.weteam.java.design.entity.gifts_factory.Bookmark;
import com.weteam.java.design.entity.gifts_factory.CanvasBag;
import com.weteam.java.design.entity.gifts_factory.Gifts;
import com.weteam.java.design.entity.gifts_factory.Pen;

public class GiftsFactory {

    public static Gifts create(int id) {
        switch (id) {
            case 1:
                return new Bookmark();
            case 2:
                return new Pen();
            case 3:
                return new CanvasBag();
            default:
                return null;
        }
    }

}
