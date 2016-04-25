package org.joda.time;

import org.joda.time.base.BaseSingleFieldPeriod;

import java.util.HashMap;
import java.util.Map;

public class Pool {

    private static Pool myInstance;
    private Map<Class, MiniPool> miniPools;


    private Pool() {
        miniPools = new HashMap<Class, MiniPool>();
        miniPools.put(Days.class, new MiniPool<Days>());
        miniPools.put(Minutes.class, new MiniPool<Minutes>());
        miniPools.put(Hours.class, new MiniPool<Hours>());
        miniPools.put(Years.class, new MiniPool<Years>());
        miniPools.put(Months.class, new MiniPool<Months>());
        miniPools.put(Weeks.class, new MiniPool<Weeks>());
    }


    private static MiniPool getMiniPool(Class clazz) {
        if (myInstance == null) {
            myInstance = new Pool();
        }
        return myInstance.miniPools.get(clazz);
    }


    public static Days retrieveDays(int numeral) {
        MiniPool<Days> daysPool = getMiniPool(Days.class);
        return daysPool.retrieve(numeral, new ElementCreator() {
            public Days create(int numeral) {
                return new Days(numeral);
            }
        });
    }

    public static Minutes retrieveMinutes(int numeral) {
        MiniPool<Minutes> minutesPool = getMiniPool(Minutes.class);
        return minutesPool.retrieve(numeral, new ElementCreator() {
            public Minutes create(int numeral) {
                return new Minutes(numeral);
            }
        });
    }


    public static Hours retrieveHours(int numeral) {
        MiniPool<Hours> minutesPool = getMiniPool(Hours.class);
        return minutesPool.retrieve(numeral, new ElementCreator() {
            public Hours create(int numeral) {
                return new Hours(numeral);
            }
        });
    }


    public static Years retrieveYears(int numeral) {
        MiniPool<Years> minutesPool = getMiniPool(Years.class);
        return minutesPool.retrieve(numeral, new ElementCreator() {
            public Years create(int numeral) {
                return new Years(numeral);
            }
        });
    }

    public static Months retrieveMonths(int numeral) {
        MiniPool<Months> minutesPool = getMiniPool(Months.class);
        return minutesPool.retrieve(numeral, new ElementCreator() {
            public Months create(int numeral) {
                return new Months(numeral);
            }
        });
    }


    public static Weeks retrieveWeeks(int numeral) {
        MiniPool<Weeks> minutesPool = getMiniPool(Weeks.class);
        return minutesPool.retrieve(numeral, new ElementCreator() {
            public Weeks create(int numeral) {
                return new Weeks(numeral);
            }
        });
    }

    interface ElementCreator {
        <E extends BaseSingleFieldPeriod> E create(int numeral);
    }

    private class MiniPool<E extends BaseSingleFieldPeriod> {
        private Map<Integer, E> elements = new HashMap<Integer, E>();

        private void add(int numeral, E element) {
            elements.put(numeral, element);
        }

        private E retrieve(int numeral, ElementCreator creator) {
            E element = elements.get(numeral);

            if (element == null) {
                element =  creator.create(numeral);
                elements.put(numeral, element);
            }

            return element;
        }

    }

}
