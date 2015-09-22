package org.meetu;

import java.util.ArrayList;
import java.util.Collection;

public class TestGeneric {
	public static <T> T test(int i) {
		if(i == 0) {
			return (T) new String("String");
		} else if (i ==1) {
			return (T) new Integer(1);
		} else {
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		Object p1 = test(0);
		if(p1 instanceof String) {
			System.out.println("String");
		}
		
		Object p2 = test(1);
		if(p2 instanceof Integer) {
			System.out.println("Integer");
		}
		Collection<String> col = new ArrayList<>();
	}
	
    public static <T> T accumulate(T[] array, T initValue) {
        if (array == null) {
            return initValue;
        }

        T result = initValue;
        for (T t : array) {
            if (false) {
            } else if (initValue instanceof Integer) {
                Integer foo = (Integer) result;
                Integer bar = (Integer) t;
                foo += bar;
                result = (T) foo;
            } else if (initValue instanceof Long) {
                Long foo = (Long) result;
                Long bar = (Long) t;
                foo += bar;
                result = (T) foo;
            } else if (initValue instanceof Float) {
                Float foo = (Float) result;
                Float bar = (Float) t;
                foo += bar;
                result = (T) foo;
            } else if (initValue instanceof Double) {
                Double foo = (Double) result;
                Double bar = (Double) t;
                foo += bar;
                result = (T) foo;
            } else {
            }
        }
        return result;
    }
}
