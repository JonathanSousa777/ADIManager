package com.adisolucoes.adimanager.model;

import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author ADI Soluções
 */
public class BeanComparator implements Comparator<Object> {

    private String getter;
    private String getter2;
    private final SortOrder sortOrder;

    public BeanComparator(String field, SortOrder sortOrder) {
        this.sortOrder = sortOrder;
        this.getter = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
        if (getter.contains(".")) {
            getter2 = getter.split("\\.")[1];
            getter2 = "get" + getter2.substring(0, 1).toUpperCase() + getter2.substring(1);
            getter = getter.split("\\.")[0];
        } else {
            getter2 = null;
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        try {
            if (o1 != null && o2 != null) {
                o1 = o1.getClass().getMethod(getter, new Class[0]).invoke(o1, new Object[0]);
                o2 = o2.getClass().getMethod(getter, new Class[0]).invoke(o2, new Object[0]);
                if (getter2 != null && o1 != null && o2 != null) {
                    o1 = o1.getClass().getMethod(getter2, new Class[0]).invoke(o1, new Object[0]);
                    o2 = o2.getClass().getMethod(getter2, new Class[0]).invoke(o2, new Object[0]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível comparar " + o1 + " com " + o2 + " por " + getter, e);
        }
        int value = (o1 == null) ? -1 : ((o2 == null) ? 1 : ((Comparable<Object>) o1).compareTo(o2));
        return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
    }
}
